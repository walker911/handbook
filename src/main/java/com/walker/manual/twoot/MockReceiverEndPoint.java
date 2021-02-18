package com.walker.manual.twoot;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/7
 */
public class MockReceiverEndPoint implements ReceiverEndPoint{

    private final List<Twoot> receivedTwoots = new ArrayList<>();

    @Override
    public void onTwoot(Twoot twoot) {
        receivedTwoots.add(twoot);
    }

    public void verifyOnTwoot(final Twoot twoot) {
        MatcherAssert.assertThat(receivedTwoots, Matchers.contains(twoot));
    }
}
