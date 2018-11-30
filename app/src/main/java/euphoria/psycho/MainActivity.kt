package euphoria.psycho

import android.Manifest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import euphoria.psycho.common.checkPermissions
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var mNavController: NavController

    private fun initialize(saveState: Bundle?) {
        setContentView(R.layout.activity_main)
        setupToolBar()
         
    }


    private fun setupToolBar() {
        setSupportActionBar(uiToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        mNavController = Navigation.findNavController(this, R.id.uiNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController, uiHackyDrawerLayout)
        NavigationUI.setupWithNavController(uiNavigationView, mNavController)
        uiNavigationView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(uiHackyDrawerLayout, mNavController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (uiHackyDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            uiHackyDrawerLayout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.activity_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET),
            savedInstanceState,
            { s -> initialize(s) })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (uiHackyDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    uiHackyDrawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    uiHackyDrawerLayout.openDrawer(GravityCompat.START)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        uiHackyDrawerLayout.closeDrawers()
        when (item.itemId) {

            R.id.action_fragment_translator ->
                mNavController.navigate(R.id.fragment_translator)
            R.id.action_language_zh
            -> mNavController.navigate(R.id.fragment_explorer)
        }
        return true
    }
}