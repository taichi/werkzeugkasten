package com.google.werkzeugkasten.mvnhack.repository;

import java.net.URL;

public interface Destination {

	void copyFrom(Context context, URL url);
}
