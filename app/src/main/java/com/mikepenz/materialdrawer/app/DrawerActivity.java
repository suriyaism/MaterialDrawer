package com.mikepenz.materialdrawer.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class DrawerActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 100000;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dark_toolbar);

        //Remove line to test RTL support
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("Suriya Krishnan").withEmail("suriya.gopalakrishnan_ext@michelin.com").withIcon("https://00e9e64bac8519b44b7cad9123252813a20fee1b390cbdacee-apidata.googleusercontent.com/download/storage/v1/b/androidpictures/o/michelin-new-logo-2017-bibendum.jpg?qk=AD5uMEtYwZ7tgoGQfngOBELbXzosvmcDXJ79eFQQHs2ZvFT6nt9h9yvPcrbRlxia9qg7lVOFnVbey4sJDat8I-fYf-mgxxQUXii7pqKtt6ncpF2W8enV59ACODDplPjQTOv9duXR3RUcfk7xnK0SuURtUwgeTCBXiC7E2lbhWTl_60O-W6mPx5AqgFybfrpgMXS1ZxuGc1f7pWGeTB8tKYgQ2JWnD46A90arHl3QAdrvaexyN-LfKx93KjkThpIqHKf2tJEwxHPnA0cMllTmpfFt83Zj3qMWj5REAsE-1N2AVvOTT7WhmQ-1Mby-ZxpaOL8HqOm79H31m9f472XwVFBVWemactjJS7tYtT7Q6AGbPW-0CGh3LYWQEwVqXCjN95NTw2n_XK5qhqFjPTs6LArEidqRUA3aJRZMjKIHhWh-jobcUnrZeSx2hVy5Wz7ty4p2ARAZlTyEeVl_EnvlsrPoG2AO_mEJVF4-Pu1np6dSZU_h2UVDXzg9rKc7ryHCvzL4anS58_erbYKqPGzuyx8w_YZL-W2HDvDt6C5h5A59hs4o0i0aQCQZrGtPAunNMopljLnwIziJY5CtyAM2RssuC15dRF3olFOF70VCVJwmpXcBJHgAB0Jgyot0HxHhdDbdxTy5NFPV4RdK_znJgDMweX7Fj_4ML19vFauLEQPmG6wf3q36B9sgdhFR_iQuWZMHEbDUM-hVAayaZZULVu3Fo6sVShui3KhQDBgmbw6XTH5gYw5tv7zvdJ4Kz2uRdveM3PW3zhZmwm1zvi5_-KEZkSI6Q_Mv-g").withIdentifier(100);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                        //profile2,
                        //profile3,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        //new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        //new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(100001)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && profile.getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.profile5).withIdentifier(count);
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }

                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new ExpandableDrawerItem().withName("System Admin").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("File Upload").withLevel(3).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2),
                                new ExpandableDrawerItem().withName("File Maintenance").withLevel(3).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2005).withSelectable(false).withSubItems(
                                        new SecondaryDrawerItem().withName("Std Ref Files").withLevel(4).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2006),
                                        new SecondaryDrawerItem().withName("System Tables").withLevel(4).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2007)
                                )
                        ),
                        new ExpandableDrawerItem().withName("User Access").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                            new SecondaryDrawerItem().withName("Add/Edit").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2008),
                            new SecondaryDrawerItem().withName("Delete").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2009)
                        ),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(21).withTag("Bullhorn")
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem
                        https://avatars3.githubusercontent.com/u/1476232?v=3&s=460
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(DrawerActivity.this, CompactHeaderDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(DrawerActivity.this, ActionBarActivity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                            } else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(DrawerActivity.this, AdvancedActivity.class);
                            } else if (drawerItem.getIdentifier() == 8) {
                                intent = new Intent(DrawerActivity.this, FullscreenDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 9) {
                                intent = new Intent(DrawerActivity.this, CustomContainerActivity.class);
                            } else if (drawerItem.getIdentifier() == 10) {
                                intent = new Intent(DrawerActivity.this, MenuDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 11) {
                                intent = new Intent(DrawerActivity.this, MiniDrawerActivity.class);
                            } else if (drawerItem.getIdentifier() == 12) {

                            } else if (drawerItem.getIdentifier() == 13) {
                                intent = new Intent(DrawerActivity.this, CollapsingToolbarActivity.class);
                            } else if (drawerItem.getIdentifier() == 14) {

                            } else if (drawerItem.getIdentifier() == 15) {

                            } else if (drawerItem.getIdentifier() == 20) {
                                intent = new LibsBuilder()
                                        .withFields(R.string.class.getFields())
                                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                                        .intent(DrawerActivity.this);
                            }
                            if (intent != null) {
                                DrawerActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
//              .withShowDrawerUntilDraggedOpened(true)
                .build();

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }

        result.updateBadge(4, new StringHolder(10 + ""));
    }

    /*
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };
    */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
