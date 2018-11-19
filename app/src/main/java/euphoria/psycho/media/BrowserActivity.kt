package euphoria.psycho.media

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class BrowserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    private lateinit var mToolbar: Toolbar
    private lateinit var mNavController: NavController

    private fun initialize(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
//        mEmptyView = findViewById(R.id.empty_view)
//        setupRecyclerView()
//        mRequestManager = Glide.with(this)
//        val list = getList("downloads".getExternalStorageFile())
//        mBrowserAdapter = BrowserAdapter(this, mRequestManager, list, ArrayList(), true)
//        mRecyclerView.adapter = mBrowserAdapter

        setupToolBar()

        //replaceFragment(R.id.container, MediaFragment())

    }

    private fun setupNavigation() {

    }

    private fun setupToolBar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavigationView = findViewById(R.id.navigationView)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController, mDrawerLayout)
        NavigationUI.setupWithNavController(mNavigationView, mNavController)
        mNavigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mDrawerLayout, mNavController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkStoragePermission(savedInstanceState, { state ->
            initialize(state)
        })
    }


}