package com.saya.coco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.saya.coco.clubs.InfoMusic;
import com.saya.coco.clubs.InfoRUBIIC;
import com.saya.coco.clubs.InfoRedCross;
import com.saya.coco.clubs.Info_Beyou;
import com.saya.coco.clubs.TcBeyou;
import com.saya.coco.clubs.TcMusic;
import com.saya.coco.clubs.TcRedCross;
import com.saya.coco.clubs.TcRubiic;

import java.util.List;

public class Adapter extends PagerAdapter {

    private final List<Model> models;
    private final Context context;
    public static Intent myIntent;

    public static Intent ii = new Intent();

    public static String clubName;
    public static int noOfStudents;


    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public Adapter(List<Model> models, Context context, Intent myIntent) {
        this.models = models;
        this.context = context;
        Adapter.myIntent = myIntent;
    }

    @Override
    public int getCount() {
        models.size();
        return models.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item, container, false);


        //Hooks
        ImageView iv_bg, iv_logo;
        TextView club_name, club_slogan, tv_info, tv_join;


        iv_bg = v.findViewById(R.id.clubBg);
        iv_logo = v.findViewById(R.id.clubLogo);
        club_name = v.findViewById(R.id.clubName);
        club_slogan = v.findViewById(R.id.clubSlogan);
        tv_info = v.findViewById(R.id.tvInfo);
        tv_join = v.findViewById(R.id.tvJoin);


        iv_bg.setImageResource(models.get(position).getIv_bg());
        iv_logo.setImageResource(models.get(position).getIv_logo());
        club_name.setText(models.get(position).getClub_name());
        club_slogan.setText(models.get(position).getClub_slogan());
        tv_info.setText(models.get(position).getTv_info());
        tv_join.setText(models.get(position).getTv_join());



        //Textview Info to open respective Info class to show about us for different clubs
        tv_info.setOnClickListener(v1 -> {
            switch (position) {
                case 0:

                    Intent intent = new Intent(v1.getContext(), Info_Beyou.class);
                    v1.getContext().startActivity(intent);

                    break;

                case 1:

                    Intent i1 = new Intent(v1.getContext(), InfoMusic.class);
                    v1.getContext().startActivity(i1);

                    break;

                case 2:

                    Intent i2 = new Intent(v1.getContext(), InfoRedCross.class);
                    v1.getContext().startActivity(i2);

                    break;

                case 3:

                    Intent i3 = new Intent(v1.getContext(), InfoRUBIIC.class);
                    v1.getContext().startActivity(i3);

                    break;
            }

        });


        //Textview Join to open respective Join class to show T&C for different clubs
        tv_join.setOnClickListener(v2 ->{
            switch (position) {
                case 0:

                    Intent i = new Intent(v2.getContext(), TcBeyou.class);
                    //ii.putExtra("cName", clubName);
                    //clubName = "Be You Club";
                    v2.getContext().startActivity(i);

                    break;

                case 1:

                    Intent i1 = new Intent(v2.getContext(), TcMusic.class);
                    //ii.putExtra("cName", clubName);
                    //clubName = "Music Club";
                    v2.getContext().startActivity(i1);

                    break;

                case 2:
                    Intent i2 = new Intent(v2.getContext(), TcRedCross.class);
                    //ii.putExtra("cName", clubName);
                    //clubName = "Red Cross Club";
                    v2.getContext().startActivity(i2);

                    break;

                case 3:
                    Intent i3 = new Intent(v2.getContext(), TcRubiic.class);
                    //ii.putExtra("cName", clubName);
                    //clubName = "RUBIIC Club";
                    v2.getContext().startActivity(i3);

                    break;

            }

            myIntent = ((Activity) context).getIntent();
            clubName = myIntent.getStringExtra("cName");
            noOfStudents = myIntent.getIntExtra("noOfStudents", 0);


        });


        container.addView(v, 0);
        return v;

    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    //Lines 135 through 195 were replaced by lines 780 through 117. Using adapter inside another adapter did not work.
    /*public static class Adapter1 extends PagerAdapter {
        public List<Info> infos;
        private final Context context;
        public Adapter1(List<Info> infos, Context context) {
            this.infos = infos;
            this.context = context;
        }
        @Override
        public int getCount() {
            return infos.size();
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View v1 = layoutInflater.inflate(R.layout.info, container, false);
            TextView club_title, about_us, club_desc;
            club_title = v1.findViewById(R.id.clubTitle);
            about_us = v1.findViewById(R.id.aboutUs);
            club_desc = v1.findViewById(R.id.clubDesc);
            club_title.setText(infos.get(position).getClub_title());
            about_us.setText(infos.get(position).getAbout_us());
            club_desc.setText(infos.get(position).getClub_desc());
            infos= new ArrayList<>();
            infos.add(new Info("BE YOU CLUB", "About Us", "Be You Club is more than just…"));
            infos.add(new Info("MUSIC CLUB", "About Us", "Music Club is more than just…"));
            infos.add(new Info("RED CROSS CLUB", "About Us", "Red Cross Club is more than just…"));
            infos.add(new Info("B YOU CLUB", "About Us", "Be You Club is more than just…"));
            container.addView(v1, 0);
            return v1;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }*/

    /*public static class Adapter2 extends PagerAdapter {
        private final List<Join> joins;
        private final Context context;
        public Adapter2(List<Join> joins, Context context) {
            this.joins = joins;
            this.context = context;
        }
        @Override
        public int getCount() {
            return joins.size();
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View v2 = layoutInflater.inflate(R.layout.join, container, false);
            TextView klab_title, tc_title, tc_subTitle, tc_club;
            klab_title = v2.findViewById(R.id.klabTitle);
            tc_title = v2.findViewById(R.id.tcTitle);
            tc_subTitle = v2.findViewById(R.id.tcSubTitle);
            tc_club = v2.findViewById(R.id.tcClub);
            klab_title.setText(joins.get(position).getKlabTitle());
            tc_title.setText(joins.get(position).getTcTitle());
            tc_subTitle.setText(joins.get(position).getTcSubTitle());
            tc_club.setText(joins.get(position).getClubTC());
            container.addView(v2, 0);
            return v2;
        }
        @Override
        public void  destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }*/

}
