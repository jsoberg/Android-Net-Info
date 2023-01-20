import com.android.build.gradle.BaseExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal val Project.android: BaseExtension
    get() = extensions.findByName("android") as BaseExtension

internal fun BaseExtension.kotlinOptions(configure: Action<KotlinJvmOptions>) =
    androidExtensions.configure("kotlinOptions", configure)

private val BaseExtension.androidExtensions: ExtensionContainer
    get() = (this as ExtensionAware).extensions