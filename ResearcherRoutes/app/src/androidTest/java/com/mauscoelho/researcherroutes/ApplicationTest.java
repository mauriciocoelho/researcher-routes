package com.mauscoelho.researcherroutes;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.mauscoelho.researcherroutes.network.interfaces.IAction;
import com.mauscoelho.researcherroutes.network.models.Routes;
import com.mauscoelho.researcherroutes.network.services.RouteService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<App> {

    private App application;

    public ApplicationTest() {
        super(App.class);
    }


}