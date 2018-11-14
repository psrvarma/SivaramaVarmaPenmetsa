package com.sky.videoondemand.parentalcontrol.service;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class ParentControlLevelTest {

    @Test
    public void shouldResolveToUniversalWhenResolveLevelIsCalledWithU() {
        // when
        ParentControlLevel resolvedLevel = ParentControlLevel.resolveLevel("U");

        // then
        Assert.assertEquals(resolvedLevel, ParentControlLevel.UNIVERSAL);
        Assert.assertThat(ParentControlLevel.resolveLevel("U").getContentCategoryLevel(), is(1));
    }

    @Test
    public void shouldResolveToParentalGuidanceWhenResolveLevelIsCalledWithPG() {
        // when
        ParentControlLevel resolvedLevel = ParentControlLevel.resolveLevel("PG");

        // then
        Assert.assertEquals(resolvedLevel, ParentControlLevel.PARENTAL_GUIDANCE);
        Assert.assertThat(ParentControlLevel.resolveLevel("PG").getContentCategoryLevel(), is(2));
    }

    @Test
    public void shouldResolveToTwelveWhenResolveLevelIsCalledWith12() {
        // when
        ParentControlLevel resolvedLevel = ParentControlLevel.resolveLevel("12");

        // then
        Assert.assertEquals(resolvedLevel, ParentControlLevel.TWELVE);
        Assert.assertThat(ParentControlLevel.resolveLevel("12").getContentCategoryLevel(), is(3));
    }

    @Test
    public void shouldResolveToFifteenWhenResolveLevelIsCalledWith15() {
        // when
        ParentControlLevel resolvedLevel = ParentControlLevel.resolveLevel("15");

        // then
        Assert.assertEquals(resolvedLevel, ParentControlLevel.FIFTEEN);
        Assert.assertThat(ParentControlLevel.resolveLevel("15").getContentCategoryLevel(), is(4));
    }

    @Test
    public void shouldResolveToEighteenWhenResolveLevelIsCalledWith18() {
        // when
        ParentControlLevel resolvedLevel = ParentControlLevel.resolveLevel("18");

        // then
        Assert.assertEquals(resolvedLevel, ParentControlLevel.EIGHTEEN);
        Assert.assertThat(ParentControlLevel.resolveLevel("18").getContentCategoryLevel(), is(5));
    }


}