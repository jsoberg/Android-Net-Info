#!/bin/bash
# Runs the gradlew update task, and then the gradlew wrapper task again to insure all files are updated.
# See https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:upgrading_wrapper.
./gradlew wrapper --gradle-version="$1" --distribution-type=bin
./gradlew wrapper