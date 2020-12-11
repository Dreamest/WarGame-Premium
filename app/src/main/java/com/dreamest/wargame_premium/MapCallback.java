package com.dreamest.wargame_premium;

import com.google.android.gms.maps.model.LatLng;

public interface MapCallback {
    void addMarker(String title, LatLng position);

    void focusPosition(LatLng position);
}
