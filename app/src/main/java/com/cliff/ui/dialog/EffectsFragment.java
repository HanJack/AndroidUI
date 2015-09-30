package com.cliff.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cliff.ui.MainActivity;
import com.cliff.ui.R;
import com.hsj.libdialog.SweetAlertDialog;
import com.hsj.libeffects.Effectstype;
import com.hsj.libeffects.NiftyDialogBuilder;

/**
 * Created by geeboo on 2015/9/7.
 * 自定义对话框组件demo
 */
public class EffectsFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static EffectsFragment newInstance(int sectionNumber) {
        EffectsFragment fragment = new EffectsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public EffectsFragment() {
    }

    private Effectstype effect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_effects, container, false);
        rootView.findViewById(R.id.fadein).setOnClickListener(this);
        rootView.findViewById(R.id.slideright).setOnClickListener(this);
        rootView.findViewById(R.id.slideleft).setOnClickListener(this);
        rootView.findViewById(R.id.slidetop).setOnClickListener(this);
        rootView.findViewById(R.id.slideBottom).setOnClickListener(this);
        rootView.findViewById(R.id.newspager).setOnClickListener(this);
        rootView.findViewById(R.id.fall).setOnClickListener(this);
        rootView.findViewById(R.id.sidefall).setOnClickListener(this);
        rootView.findViewById(R.id.shake).setOnClickListener(this);
        rootView.findViewById(R.id.fliph).setOnClickListener(this);
        rootView.findViewById(R.id.flipv).setOnClickListener(this);
        rootView.findViewById(R.id.rotatebottom).setOnClickListener(this);
        rootView.findViewById(R.id.rotateleft).setOnClickListener(this);
        rootView.findViewById(R.id.slit).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onClick(View v) {
        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
        switch (v.getId()) {
            case R.id.fadein:
                effect = Effectstype.FADEIN;
                break;
            case R.id.slideright:
                effect = Effectstype.SLIDERIGHT;
                break;
            case R.id.slideleft:
                effect = Effectstype.SLIDELEFT;
                break;
            case R.id.slidetop:
                effect = Effectstype.SLIDETOP;
                break;
            case R.id.slideBottom:
                effect = Effectstype.SLIDEBOTTOM;
                break;
            case R.id.newspager:
                effect = Effectstype.NEWSPAGER;
                break;
            case R.id.fall:
                effect = Effectstype.FALL;
                break;
            case R.id.sidefall:
                effect = Effectstype.SIDEFILL;
                break;
            case R.id.fliph:
                effect = Effectstype.FLIPH;
                break;
            case R.id.flipv:
                effect = Effectstype.FLIPV;
                break;
            case R.id.rotatebottom:
                effect = Effectstype.ROTATEBOTTOM;
                break;
            case R.id.rotateleft:
                effect = Effectstype.ROTATELEFT;
                break;
            case R.id.slit:
                effect = Effectstype.SLIT;
                break;
            case R.id.shake:
                effect = Effectstype.SHAKE;
                break;
        }

        dialogBuilder
                .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("This is a modal Dialog.")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFF")                                //def
                .withIcon(getResources().getDrawable(R.drawable.ic_launcher))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(1000)                                          //def    数值约大动画越明显
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .setCustomView(R.layout.custom_view, v.getContext())         //.setCustomView(View or ResId,context)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


    }
}
