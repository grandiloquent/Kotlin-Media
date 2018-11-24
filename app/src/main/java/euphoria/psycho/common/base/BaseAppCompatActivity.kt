package euphoria.psycho.common.base

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.TaskStackBuilder

class BaseAppCompatActivity : AppCompatActivity() {

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
// Add an additional content view to the activity.
        super.addContentView(view, params)
    }
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
// Called to process key events.
        return super.dispatchKeyEvent(event)
    }
    override fun getDelegate(): AppCompatDelegate {
//
        return super.getDelegate()
    }
    override fun getDrawerToggleDelegate(): ActionBarDrawerToggle.Delegate? {
//
        return super.getDrawerToggleDelegate()
    }
    override fun getMenuInflater(): MenuInflater {
// Returns a MenuInflater with this context.
        return super.getMenuInflater()
    }
    override fun getResources(): Resources {
// Returns a Resources instance for the application's package.
        return super.getResources()
    }
    override fun getSupportActionBar(): ActionBar? {
// Support library version of getActionBar().
        return super.getSupportActionBar()
    }
    override fun getSupportParentActivityIntent(): Intent? {
// Obtain an Intent that will launch an explicit target activity  specified by sourceActivity's PARENT_ACTIVITY &lt;meta-data&gt;  element in the application's manifest.
        return super.getSupportParentActivityIntent()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
// Dispatch configuration change to all fragments.
        super.onConfigurationChanged(newConfig)
    }
    override fun onContentChanged() {
// This hook is called whenever the content view of the screen changes  (due to a call to  Window.setContentView or  Window.addContentView).
        super.onContentChanged()
    }
    override fun onCreate(savedInstanceState: Bundle?){
// Perform initialization of all fragments and loaders.
        super.onCreate(savedInstanceState)
    }
    override fun onCreateSupportNavigateUpTaskStack(builder: TaskStackBuilder) {
// Support version of onCreateNavigateUpTaskStack(android.app.TaskStackBuilder).
        super.onCreateSupportNavigateUpTaskStack(builder)
    }
    override fun onDestroy(){
// Destroy all fragments and loaders.
        super.onDestroy()
    }
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
// Called when a panel's menu is opened by the user.
        return super.onMenuOpened(featureId, menu)
    }
    override fun onPanelClosed(featureId: Int, menu: Menu) {
// Call onOptionsMenuClosed() on fragments.
        super.onPanelClosed(featureId, menu)
    }
    override fun onPostCreate(savedInstanceState:Bundle?){
// Called when activity start-up is complete (after onStart()  and onRestoreInstanceState(Bundle) have been called).
        super.onPostCreate(savedInstanceState)
    }
    override fun onPostResume(){
// Dispatch onResume() to fragments.
        super.onPostResume()
    }
    override fun onPrepareSupportNavigateUpTaskStack(builder: TaskStackBuilder) {
// Support version of onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder).
        super.onPrepareSupportNavigateUpTaskStack(builder)
    }
    override fun onSaveInstanceState(outState:Bundle){
// Save all appropriate fragment state.
        super.onSaveInstanceState(outState)
    }
    override fun onStop(){
// Dispatch onStop() to all fragments.
        super.onStop()
    }
    override fun onSupportContentChanged() {
// This method is deprecated.     Use onContentChanged() instead.
        super.onSupportContentChanged()
    }
    override fun onSupportNavigateUp(): Boolean {
// This method is called whenever the user chooses to navigate Up within your application's  activity hierarchy from the action bar.
        return super.onSupportNavigateUp()
    }
    override fun onTitleChanged(title:CharSequence,color:Int){
//
        super.onTitleChanged(title,color)
    }
    override fun setContentView(view: View) {
// Set the activity content to an explicit view.
        super.setContentView(view)
    }
    override fun setContentView(layoutResID: Int) {
// Set the activity content from a layout resource.
        super.setContentView(layoutResID)
    }
    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
// Set the activity content to an explicit view.
        super.setContentView(view, params)
    }
    override fun setSupportProgress(progress: Int) {
// This method is deprecated.     Progress bars are no longer provided in AppCompat.
        super.setSupportProgress(progress)
    }
    override fun setSupportProgressBarIndeterminate(indeterminate: Boolean) {
// This method is deprecated.     Progress bars are no longer provided in AppCompat.
        super.setSupportProgressBarIndeterminate(indeterminate)
    }
    override fun setSupportProgressBarIndeterminateVisibility(visible: Boolean) {
// This method is deprecated.     Progress bars are no longer provided in AppCompat.
        super.setSupportProgressBarIndeterminateVisibility(visible)
    }
    override fun setSupportProgressBarVisibility(visible: Boolean) {
// This method is deprecated.     Progress bars are no longer provided in AppCompat.
        super.setSupportProgressBarVisibility(visible)
    }
    override fun setTheme(resid: Int) {
// Set the base theme for this context.
        super.setTheme(resid)
    }
    override fun supportInvalidateOptionsMenu() {
// Support library version of invalidateOptionsMenu().
        super.supportInvalidateOptionsMenu()
    }
    override fun supportNavigateUpTo(upIntent: Intent) {
// Navigate from sourceActivity to the activity specified by upIntent, finishing sourceActivity  in the process.
        super.supportNavigateUpTo(upIntent)
    }
    override fun supportRequestWindowFeature(featureId: Int): Boolean {
// Enable extended support library window features.
        return super.supportRequestWindowFeature(featureId)
    }
    override fun supportShouldUpRecreateTask(targetIntent: Intent): Boolean {
// Returns true if sourceActivity should recreate the task when navigating 'up'  by using targetIntent.
        return super.supportShouldUpRecreateTask(targetIntent)
    }

}