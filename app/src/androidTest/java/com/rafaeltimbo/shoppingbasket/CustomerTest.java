package com.rafaeltimbo.shoppingbasket;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CustomerTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void customerTest() throws InterruptedException {
        Thread.sleep(500);
        ViewInteraction textView = onView(
                allOf(withId(R.id.appTitle), withText("Shopping Basket"),
                        isDisplayed()));
        textView.check(matches(withText("Shopping Basket")));

        ViewInteraction button = onView(
                allOf(withId(R.id.loginBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.signupBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.basketImage), withContentDescription("Shopping Basket"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.basketImage), withContentDescription("Shopping Basket"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signupBtn), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.customerLoginBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        7),
                                0),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.clerkLoginBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        7),
                                1),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.usernameEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.usernameEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("rafael"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.passwordEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("rafael"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.firstnameEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("Rafael"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.firstnameEditText), withText("Rafael"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.lastnameEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Timbo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.lastnameEditText), withText("Timbo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.addressEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("Centennial College"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.addressEditText), withText("Centennial College"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(pressImeActionButton());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.cityEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("Toronto"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.cityEditText), withText("Toronto"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.postalCodeEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        6),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("ON M1G 3T8"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.postalCodeEditText), withText("ON M1G 3T8"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        6),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.customerLoginBtn), withText("Sign up as Customer"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        7),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        /*
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.appTitle), withText("Shopping Basket"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("Shopping Basket")));
        */

        ViewInteraction button5 = onView(
                allOf(withId(R.id.loginBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                0),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.usernameLoginScreen),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.usernameLoginScreen),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText15.perform(replaceText("rafael"), closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.usernameLoginScreen), withText("rafael"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(pressImeActionButton());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.passwordLoginScreen),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("rafael"), closeSoftKeyboard());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.passwordLoginScreen), withText("rafael"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText18.perform(pressImeActionButton());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.loginBtn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.listOrdersHeader), withText("Orders:"),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.viewProductsBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.viewProductsBtn), withText("View Products"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withText("Current Cart:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.placeOrderBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        /*
        DataInteraction textView5 = onData(
                allOf(withId(R.id.productPrice),// withText("$0.00"),
                        isDisplayed()));
        textView5.check(matches(withText("$0.00")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.productPrice), withText("$0.00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("$0.00")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.productPrice), withText("$0.00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                2),
                        isDisplayed()));
        textView7.check(matches(withText("$0.00")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.totalAccumulator), withText("$0.00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        textView8.check(matches(withText("$0.00")));
        */

        checkCartHasZeroItems().perform(click());

        ViewInteraction button8 = onView(
                allOf(withId(R.id.addToCartBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        button8.check(matches(isDisplayed()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.productDetailsQuantity), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        textView9.check(matches(withText("0")));

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.productDetailsNewQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText19.perform(click());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.productDetailsNewQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText20.perform(replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.productDetailsNewQuantity), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText21.perform(pressImeActionButton());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.addToCartBtn), withText("Add to cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton5.perform(click());

        /*
        ViewInteraction textView11 = onView(
                allOf(withId(R.id.productPrice), withText("$86.01"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                2),
                        isDisplayed()));
        textView11.check(matches(withText("$86.01")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.productQuantity), withText("3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                3),
                        isDisplayed()));
        textView12.check(matches(withText("3")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.totalAccumulator), withText("$86.01"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        textView13.check(matches(withText("$86.01")));
        */

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.placeOrderBtn), withText("Place Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.orderPrice), withText("$86.01"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView14.check(matches(withText("$86.01")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.orderCustomer), withText("Rafael Timbo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                2),
                        isDisplayed()));
        textView15.check(matches(withText("Rafael Timbo")));

        /*
        ViewInteraction textView16 = onView(
                allOf(withId(R.id.orderStatus), withText("Processing"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                3),
                        isDisplayed()));
        textView16.check(matches(withText("Processing")));
        */

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.viewProductsBtn), withText("View Products"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton7.perform(click());

        /*
        ViewInteraction textView17 = onView(
                allOf(withId(R.id.productPrice), withText("$0.00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        2),
                                2),
                        isDisplayed()));
        textView17.check(matches(withText("$0.00")));
        */

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(R.id.fragment),
                                0)))
                .atPosition(1);
        relativeLayout2.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.productDetailsNewQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText22.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.productDetailsNewQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText23.perform(replaceText("4"), closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.productDetailsNewQuantity), withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText24.perform(pressImeActionButton());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.addToCartBtn), withText("Add to cart"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton8.perform(click());

        /*
        ViewInteraction textView18 = onView(
                allOf(withId(R.id.productQuantity), withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                3),
                        isDisplayed()));
        textView18.check(matches(withText("4")));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.productPrice), withText("$2879.96"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        1),
                                2),
                        isDisplayed()));
        textView19.check(matches(withText("$2879.96")));
        */

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.placeOrderBtn), withText("Place Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction relativeLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.list),
                                childAtPosition(
                                        withId(R.id.orderFragment),
                                        0)),
                        1),
                        isDisplayed()));
        relativeLayout3.check(matches(isDisplayed()));

        DataInteraction relativeLayout4 = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withId(R.id.orderFragment),
                                0)))
                .atPosition(0);
        relativeLayout4.perform(click());

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.orderPrice), withText("$86.01"),
                        childAtPosition(
                                allOf(withId(R.id.includedLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView20.check(matches(withText("$86.01")));

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.orderCustomer), withText("Rafael Timbo"),
                        childAtPosition(
                                allOf(withId(R.id.includedLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        textView21.check(matches(withText("Rafael Timbo")));

        /*
        ViewInteraction textView22 = onView(
                allOf(withId(R.id.orderStatus), withText("Processing"),
                        childAtPosition(
                                allOf(withId(R.id.includedLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                3),
                        isDisplayed()));
        textView22.check(matches(withText("Processing")));
        */

        ViewInteraction button9 = onView(
                allOf(withId(R.id.cancelOrderBtn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        button9.check(matches(isDisplayed()));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.cancelOrderBtn), withText("Cancel Order"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton10.perform(click());

    }

    private DataInteraction checkCartHasZeroItems() {
        DataInteraction relativeLayout = null;

        for (int i = 0; i < 4 ; i++) {
            DataInteraction firstItem = onData(anything())
                    .inAdapterView(allOf(withId(android.R.id.list),
                            childAtPosition(
                                    withId(R.id.fragment),
                                    0)))
                    .atPosition(i);
            relativeLayout = firstItem.onChildView(withId(R.id.productQuantity));
            relativeLayout.check(matches(
                    withText("0")
            ));
            relativeLayout = firstItem.onChildView(withId(R.id.productPrice));
            relativeLayout.check(matches(
                    withText("$0.00")
            ));
            //relativeLayout.perform(click());
        }
        return relativeLayout;
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
