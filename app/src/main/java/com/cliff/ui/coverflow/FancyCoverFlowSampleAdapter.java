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

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cliff.libcoverflow.FancyCoverFlow;
import com.cliff.libcoverflow.FancyCoverFlowAdapter;
import com.cliff.ui.R;

import java.util.ArrayList;
import java.util.List;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

    // =============================================================================
    // Private members
    // =============================================================================

    private List<Integer> data = new ArrayList<Integer>();

    public FancyCoverFlowSampleAdapter() {
        data.add(R.drawable.guide01);
        data.add(R.drawable.guide02);
        data.add(R.drawable.guide03);
        data.add(R.drawable.guide01);
        data.add(R.drawable.guide02);
        data.add(R.drawable.guide03);
    }

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    @Override
    public int getCount() {
        return data.size() > 0 ? Integer.MAX_VALUE : data.size();
    }

    @Override
    public Integer getItem(int i) {
        return data.get(i % data.size());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void remove(int index) {
        data.remove(index % data.size());
        notifyDataSetChanged();
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;

        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 400));

        }

        imageView.setImageResource(this.getItem(i));
        return imageView;
    }
}
