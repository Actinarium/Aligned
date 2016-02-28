/*
 * Copyright (C) 2015 Actinarium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.actinarium.aligned.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.actinarium.aligned.sample.overlay.CheckersLayer;
import com.actinarium.aligned.sample.overlay.LayoutBounds;
import com.actinarium.rhythm.RhythmDrawable;
import com.actinarium.rhythm.RhythmGroup;
import com.actinarium.rhythm.RhythmOverlay;
import com.actinarium.rhythm.config.RhythmOverlayInflater;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        // Views without tender, love, and care
        TextView v12w = (TextView) findViewById(R.id.copy_12_w);
        TextView v13w = (TextView) findViewById(R.id.copy_13_w);
        TextView v14w = (TextView) findViewById(R.id.copy_14_w);
        TextView v16w = (TextView) findViewById(R.id.copy_16_w);
        TextView v18w = (TextView) findViewById(R.id.copy_18_w);
        TextView v24w = (TextView) findViewById(R.id.copy_24_w);

        // Views with tender, love, and care
        TextView v12 = (TextView) findViewById(R.id.copy_12);
        TextView v13 = (TextView) findViewById(R.id.copy_13);
        TextView v14 = (TextView) findViewById(R.id.copy_14);
        TextView v16 = (TextView) findViewById(R.id.copy_16);
        TextView v18 = (TextView) findViewById(R.id.copy_18);
        TextView v24 = (TextView) findViewById(R.id.copy_24);

        v12w.setText(getString(R.string.piece_of_copy_not_aligned, 12));
        v13w.setText(getString(R.string.piece_of_copy_not_aligned, 13));
        v14w.setText(getString(R.string.piece_of_copy_not_aligned, 14));
        v16w.setText(getString(R.string.piece_of_copy_not_aligned, 16));
        v18w.setText(getString(R.string.piece_of_copy_not_aligned, 18));
        v24w.setText(getString(R.string.piece_of_copy_not_aligned, 24));

        v12.setText(getString(R.string.piece_of_copy_aligned, 12, 16, 8));
        v13.setText(getString(R.string.piece_of_copy_aligned, 13, 16, 8));
        v14.setText(getString(R.string.piece_of_copy_aligned, 14, 16, 8));
        v16.setText(getString(R.string.piece_of_copy_aligned, 16, 20, 8));
        v18.setText(getString(R.string.piece_of_copy_aligned, 18, 24, 8));
        v24.setText(getString(R.string.piece_of_copy_aligned, 24, 32, 8));

        // Set up Rhythm
        RhythmOverlayInflater inflater = RhythmOverlayInflater.createDefault(this);
        inflater.registerFactory(CheckersLayer.Factory.LAYER_TYPE, new CheckersLayer.Factory());
        inflater.registerFactory(LayoutBounds.Factory.LAYER_TYPE, new LayoutBounds.Factory());

        List<RhythmOverlay> overlays = inflater.inflate(R.raw.overlays);

        // Draw layout bounds for all text views
        new RhythmGroup()
                .addOverlay(overlays.get(0))
                .decorate(v12, v13, v14, v16, v18, v24, v12w, v13w, v14w, v16w, v18w, v24w);

        // Draw baseline grid under all content
        findViewById(R.id.content).setBackgroundDrawable(new RhythmDrawable(overlays.get(1)));
    }

    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(getResources().getDimension(R.dimen.actionBarElevation));
    }

}
