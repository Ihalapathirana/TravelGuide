package project.guide.anu.travellanka;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Anu on 6/23/2015.
 */
public class NavigationDrawer extends Fragment {






        private ActionBarDrawerToggle drawerToggle;
        private DrawerLayout mDrawerlLayout;

        ListView listView;

        ArrayList<SeachPlace> listItems=new ArrayList<SeachPlace>();

        //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW

        public NavigationDrawer() {

        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_navigation_drawer_city_view, container, false);
            listView= (ListView) view.findViewById(R.id.listViewNavBar);
            MyNavigationListAdapter adapter=new MyNavigationListAdapter(getActivity(),listItems);
            // add item to navigation drawer
            listItems.add(new SeachPlace("  Bank", R.drawable.bank));
            listItems.add(new SeachPlace("  Bus Stops", R.drawable.bus));
            listItems.add(new SeachPlace("  Gas Filling Station", R.drawable.gas));
            listItems.add(new SeachPlace("  Hospital", R.drawable.hospital));
            listItems.add(new SeachPlace("  Hotel", R.drawable.hotel));
            listItems.add(new SeachPlace("  Restaurants", R.drawable.rest));
            listItems.add(new SeachPlace("  Attraction places", R.drawable.touristattraction));
            listItems.add(new SeachPlace("  Train", R.drawable.train));

            // set list view to adapter
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                            {
                                                Intent intent;
                                                @Override
                                                public void onItemClick (AdapterView < ? > arg0, View arg1,int pos, long id){
                                                    // TODO Auto-generated method stub
                                                    if(pos==0) {

                                                        intent = new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==1){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==2){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==3){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==4){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==5){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }
                                                    else if(pos==6){

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }else{

                                                        intent=new Intent(getActivity(), ActivityPlaceListAndMap.class);
                                                        startActivity(intent);
                                                    }



                                                }
                                            }

            );


            return view;
        }


        public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
            mDrawerlLayout = drawerLayout;
            drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getActivity().invalidateOptionsMenu();
                }

                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);

                    getActivity().invalidateOptionsMenu();
                }

                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    if (slideOffset < 0.5) {
                        toolbar.setAlpha(1 - slideOffset);
                    }
                }
            };

            mDrawerlLayout.setDrawerListener(drawerToggle);
            mDrawerlLayout.post(new Runnable() {
                @Override
                public void run() {
                    drawerToggle.syncState();
                }
            });


        }




    }


