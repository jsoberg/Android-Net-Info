package com.soberg.netinfo.data.ipconfig

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.soberg.netinfo.base.type.geodetic.Country
import com.soberg.netinfo.base.type.geodetic.GeodeticInformationTestFixtures
import com.soberg.netinfo.base.type.geodetic.Location
import com.soberg.netinfo.base.type.geodetic.Region
import com.soberg.netinfo.base.type.geodetic.ZipCode
import com.soberg.netinfo.base.type.network.IpAddress
import com.soberg.netinfo.data.ipconfig.IpConfigWanInfoRepository.HttpQuery
import com.soberg.netinfo.domain.model.WanInfoTestFixtures
import com.soberg.netinfo.domain.wan.WanInfoRepository
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.net.ConnectException
import java.net.UnknownHostException

internal class IpConfigWanInfoRepositoryTest {

    private val ioDispatcher = StandardTestDispatcher()

    @Test
    fun `successfully parse wan info for 200 with only ip`() = runTest(ioDispatcher) {
        val repo = create(
            bodyContent = """
                {
                    "ip": "127.0.0.1"
                }
            """.trimIndent(),
            statusCode = HttpStatusCode.OK,
        )
        val result = repo.loadWanInfo()
        assertThat(result).isInstanceOf(WanInfoRepository.Result.Success::class.java)
        val success = result as WanInfoRepository.Result.Success
        assertThat(success.wanInfo).isEqualTo(
            WanInfoTestFixtures.get(
                ip = IpAddress("127.0.0.1"),
                ispGeoInfo = null,
            )
        )
    }

    @Test
    fun `successfully parse wan info for 200 with ip and partial geo info`() =
        runTest(ioDispatcher) {
            val repo = create(
                bodyContent = """
                {
                    "ip": "127.0.0.1",
                    "country": "United States",
                    "region_name": "New Jersey"
                }
            """.trimIndent(),
                statusCode = HttpStatusCode.OK,
            )
            val result = repo.loadWanInfo()
            assertThat(result).isInstanceOf(WanInfoRepository.Result.Success::class.java)
            val success = result as WanInfoRepository.Result.Success
            assertThat(success.wanInfo).isEqualTo(
                WanInfoTestFixtures.get(
                    ip = IpAddress("127.0.0.1"),
                    ispGeoInfo = null,
                )
            )
        }

    @Test
    fun `successfully parse wan info for 200 with ip and geo info`() = runTest(ioDispatcher) {
        val repo = create(
            bodyContent = """
                {
                    "ip": "127.0.0.1",
                    "country": "United States",
                    "country_iso": "US",
                    "region_name": "New Jersey",
                    "region_code": "NJ",
                    "zip_code": "08601",
                    "city": "Trenton",
                    "latitude": 40.2206,
                    "longitude": -74.7597
                }
            """.trimIndent(),
            statusCode = HttpStatusCode.OK,
        )
        val result = repo.loadWanInfo()
        assertThat(result).isInstanceOf(WanInfoRepository.Result.Success::class.java)
        val success = result as WanInfoRepository.Result.Success
        assertThat(success.wanInfo).isEqualTo(
            WanInfoTestFixtures.get(
                ip = IpAddress("127.0.0.1"),
                ispGeoInfo = GeodeticInformationTestFixtures.get(
                    country = Country(
                        name = "United States",
                        iso = "US",
                    ),
                    region = Region(
                        name = "New Jersey",
                        code = "NJ",
                    ),
                    zipCode = ZipCode("08601"),
                    cityName = "Trenton",
                    location = Location(
                        latitude = 40.2206,
                        longitude = -74.7597,
                    )
                ),
            )
        )
    }

    @Test
    fun `fail to parse wan info for any response that isn't a 200 status code`() =
        runTest(ioDispatcher) {
            for (statusCode in HttpStatusCode.allStatusCodes - HttpStatusCode.OK) {
                val repo = create(
                    bodyContent = """{"ip":"127.0.0.1"}""",
                    statusCode = statusCode,
                )
                val result = repo.loadWanInfo()
                assertThat(result).isEqualTo(WanInfoRepository.Result.Error)
            }
        }

    @Test
    fun `fail to parse wan info for 200 status code response with no body`() =
        runTest(ioDispatcher) {
            val repo = create(
                bodyContent = "",
                statusCode = HttpStatusCode.OK,
            )
            val result = repo.loadWanInfo()
            assertThat(result).isEqualTo(WanInfoRepository.Result.Error)
        }

    @Test
    fun `retry when UnknownHostException received`() =
        runTest(ioDispatcher) {
            val query: HttpQuery = mockk {
                coEvery { this@mockk.invoke(IpConfigUrl.Main) } throws UnknownHostException("Test")
            }
            val repo = IpConfigWanInfoRepository(ioDispatcher, query)

            val result = repo.loadWanInfo()
            assertThat(result).isEqualTo(WanInfoRepository.Result.Error)
            coVerify(exactly = 2) { query.invoke(IpConfigUrl.Main) }
        }

    @Test
    fun `retry when ConnectException received`() =
        runTest(ioDispatcher) {
            val query: HttpQuery = mockk {
                coEvery { this@mockk.invoke(IpConfigUrl.Main) } throws ConnectException("Test")
            }
            val repo = IpConfigWanInfoRepository(ioDispatcher, query)

            val result = repo.loadWanInfo()
            assertThat(result).isEqualTo(WanInfoRepository.Result.Error)
            coVerify(exactly = 2) { query.invoke(IpConfigUrl.Main) }
        }

    @Test
    fun `not retry when generic exception received`() =
        runTest(ioDispatcher) {
            val query: HttpQuery = mockk {
                coEvery { this@mockk.invoke(IpConfigUrl.Main) } throws IllegalStateException("Test")
            }
            val repo = IpConfigWanInfoRepository(ioDispatcher, query)

            val result = repo.loadWanInfo()
            assertThat(result).isEqualTo(WanInfoRepository.Result.Error)
            coVerify(exactly = 1) { query.invoke(IpConfigUrl.Main) }
        }

    private fun create(
        bodyContent: String,
        statusCode: HttpStatusCode,
    ): IpConfigWanInfoRepository {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(bodyContent),
                status = statusCode,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = IpConfigKtorClient.create(mockEngine)
        val query = HttpQuery { url -> client.get(url) }
        return IpConfigWanInfoRepository(ioDispatcher, query)
    }
}