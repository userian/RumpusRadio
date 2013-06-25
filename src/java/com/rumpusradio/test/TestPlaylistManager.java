package com.rumpusradio.test;

import com.rumpusradio.util.PlaylistManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestPlaylistManager extends TestCase {

    public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestPlaylistManager.class);
    }

	public void testGetCurrentArtistName() {
        
		PlaylistManager playlistManager = new PlaylistManager();
		assertTrue( playlistManager.getCurrentArtistName() != null );
			
	}
}


