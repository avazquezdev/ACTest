#!/bin/bash

# This script fetches and build the appcenter framework binary
set -e
mkdir -p libs
rm -f Cartfile
rm -rf Carthage
rm -rf libs/*.framework

echo 'github "microsoft/appcenter-sdk-apple" "4.0.0"' > Cartfile
carthage update
cp -r Carthage/Build/iOS/AppCenter.framework libs/
cp -r Carthage/Build/iOS/AppCenterAnalytics.framework libs/
cp -r Carthage/Build/iOS/AppCenterCrashes.framework libs/
rm -r Carthage
rm -f Cartfile
rm -f Cartfile.resolved
