package com.example.actest;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.NSTextAlignment;
import org.robovm.apple.uikit.UIButton;
import org.robovm.apple.uikit.UIButtonType;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIControl;
import org.robovm.apple.uikit.UIControlState;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UILabel;
import org.robovm.apple.uikit.UIView;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.pods.appcenter.crashes.MSACCrashes;

import java.util.Date;

public class MyViewController extends UIViewController {
    private final UIButton button;

    public MyViewController() {
        // Get the view of this view controller.
        UIView view = getView();

        // Setup background.
        view.setBackgroundColor(UIColor.white());

        // Setup button.
        button = new UIButton(UIButtonType.RoundedRect);
        button.setFrame(new CGRect(20, 150, 400, 40));
        button.setTitle("Send a sample crash", UIControlState.Normal);
        button.getTitleLabel().setFont(UIFont.getBoldSystemFont(22));

        button.addOnTouchUpInsideListener(new UIControl.OnTouchUpInsideListener() {
            @Override
            public void onTouchUpInside(UIControl uiControl, UIEvent uiEvent) {
                //MSACCrashes.generateTestCrash();
                throw new RuntimeException("Test Crash AppCenter " + new Date());
            }
        });

        view.addSubview(button);
    }
}
