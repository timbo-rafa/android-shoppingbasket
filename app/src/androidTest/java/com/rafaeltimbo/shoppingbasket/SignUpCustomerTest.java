package com.rafaeltimbo.shoppingbasket;


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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignUpCustomerTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void signUpCustomerTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signupBtn), withText("Sign Up"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

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
                allOf(withId(R.id.lastnameEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        3),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Timbo"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.addressEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Centennial College"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.addressEditText), withText("Centennial College"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.cityEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("Toronto"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.cityEditText), withText("Toronto"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        5),
                                1),
                        isDisplayed()));
        appCompatEditText9.perform(pressImeActionButton());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.postalCodeEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        6),
                                1),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("ON M1G 3T8"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.postalCodeEditText), withText("ON M1G 3T8"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        6),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.customerLoginBtn), withText("Sign up as Customer"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.loginTable),
                                        7),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

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
