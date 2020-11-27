package com.example.actest;

import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSException;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.pods.appcenter.analytics.MSACAnalytics;
import org.robovm.pods.appcenter.analytics.MSACEventProperties;
import org.robovm.pods.appcenter.core.MSACAppCenter;
import org.robovm.pods.appcenter.crashes.MSACCrashes;
import org.robovm.rt.Signals;

public class Actest extends UIApplicationDelegateAdapter {
    private UIWindow window;
    private MyViewController rootViewController;

    private static final String appSecret ="XXX";

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {

        //Set up AppCenter
        MSACAppCenter.configure(appSecret);
        MSACAppCenter.startService(MSACAnalytics.class);

        Signals.installSignals(() -> MSACAppCenter.startService(MSACCrashes.class));

        MSACCrashes.setEnabled(true);
        MSACAnalytics.setEnabled(true);

        // Turn any unhandled Java exceptions into NSErrors
        NSException.registerDefaultJavaUncaughtExceptionHandler();

        MSACAppCenter.setUserId("UserIDTest");

        /*Track event*/
        MSACEventProperties eventProperties = new MSACEventProperties();
        eventProperties.setString("Event","1");
        MSACAnalytics.trackEvent("Start app",eventProperties);

        // Set up the view controller.
        rootViewController = new MyViewController();

        // Create a new window at screen size.
        window = new UIWindow(UIScreen.getMainScreen().getBounds());
        // Set the view controller as the root controller for the window.
        window.setRootViewController(rootViewController);
        // Make the window visible.
        window.makeKeyAndVisible();

        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, Actest.class);
        }
    }
}
