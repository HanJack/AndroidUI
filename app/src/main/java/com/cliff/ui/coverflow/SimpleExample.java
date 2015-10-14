/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.cliff.ui.coverflow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cliff.libcoverflow.CoverFlowExt;
import com.cliff.ui.R;

import java.util.logging.Handler;

public class SimpleExample extends Activity {

    // =============================================================================
    // Child views
    // =============================================================================

    private CoverFlowExt fancyCoverFlow;

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cf_main);

        this.fancyCoverFlow = (CoverFlowExt) this.findViewById(R.id.fancyCoverFlow);
        final FancyCoverFlowSampleAdapter adapter = new FancyCoverFlowSampleAdapter();
        this.fancyCoverFlow.setAdapter(adapter);
        this.fancyCoverFlow.setUnselectedAlpha(0.8f);
        this.fancyCoverFlow.setUnselectedSaturation(0.0f);
        this.fancyCoverFlow.setUnselectedScale(0.9f);
        this.fancyCoverFlow.setSpacing(10);
        this.fancyCoverFlow.setMaxRotation(0);
        this.fancyCoverFlow.setScaleDownGravity(0.5f);
        this.fancyCoverFlow.setActionDistance(CoverFlowExt.ACTION_DISTANCE_AUTO);
        fancyCoverFlow.setMoveBackListener(new CoverFlowExt.OnMoveBackListener() {
            @Override
            public void onMoveback(View view, final int position) {
                Log.i("Test", view + "----------" + position);
                fancyCoverFlow.removeView(view, position);
                fancyCoverFlow.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.remove(position);
                    }
                }, 300);

            }
        });
    }

    // =============================================================================
    // Private classes
    // =============================================================================


}
