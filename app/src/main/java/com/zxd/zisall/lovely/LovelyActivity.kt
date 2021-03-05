package com.zxd.zisall.lovely

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zxd.zisall.R

class LovelyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lovely)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // 不能设置为NoActionBar
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            resetToDefaultIcon(navView)
            when (it.itemId) {
                R.id.navigation_home -> {
                    it.setIcon(R.mipmap.z_home)
                    NavigationUI.onNavDestinationSelected(it, navController)
                    true
                }
                R.id.navigation_dashboard -> {
                    it.setIcon(R.mipmap.z_home)
                    NavigationUI.onNavDestinationSelected(it, navController)
                    true
                }
                R.id.navigation_notifications -> {
                    it.setIcon(R.mipmap.z_home)
                    NavigationUI.onNavDestinationSelected(it, navController)
                    true
                }
                R.id.navigation_notifications2 -> {
                    it.setIcon(R.mipmap.z_home)
                    NavigationUI.onNavDestinationSelected(it, navController)
                    true
                }
                R.id.navigation_notifications3 -> {
                    it.setIcon(R.mipmap.z_home)
                    NavigationUI.onNavDestinationSelected(it, navController)
                    true
                }
            }
            false
        })
    }

    private fun resetToDefaultIcon(navView: BottomNavigationView) {
        navView.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_black_24dp)
        navView.menu.findItem(R.id.navigation_dashboard).setIcon(R.drawable.ic_dashboard_black_24dp)
        navView.menu.findItem(R.id.navigation_notifications)
            .setIcon(R.drawable.ic_notifications_black_24dp)
        navView.menu.findItem(R.id.navigation_notifications2)
            .setIcon(R.drawable.ic_notifications_black_24dp)
        navView.menu.findItem(R.id.navigation_notifications3)
            .setIcon(R.drawable.ic_notifications_black_24dp)
    }
}