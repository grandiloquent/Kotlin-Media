package euphoria.psycho.common.base

import android.app.*
import android.app.assist.AssistContent
import android.content.*
import android.content.res.Configuration
import android.content.res.Resources
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.transition.Scene
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.widget.Toolbar
import java.io.FileDescriptor
import java.io.PrintWriter

class BaseActivity : Activity() {

    override fun addContentView(view: View, params: ViewGroup.LayoutParams) {
// Add an additional content view to the activity.
        super.addContentView(view, params)
    }
    override fun closeContextMenu() {
// Programmatically closes the most recently opened context menu, if showing.
        super.closeContextMenu()
    }
    override fun closeOptionsMenu() {
// Progammatically closes the options menu.
        super.closeOptionsMenu()
    }
    override fun createPendingResult(requestCode: Int, data: Intent, flags: Int): PendingIntent {
// Create a new PendingIntent object which you can hand to others  for them to use to send result data back to your  onActivityResult(int, int, Intent) callback.
        return super.createPendingResult(requestCode, data, flags)
    }
    override fun dispatchGenericMotionEvent(ev: MotionEvent): Boolean {
// Called to process generic motion events.
        return super.dispatchGenericMotionEvent(ev)
    }
    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
// Called to process key events.
        return super.dispatchKeyEvent(event)
    }
    override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
// Called to process a key shortcut event.
        return super.dispatchKeyShortcutEvent(event)
    }
    override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent): Boolean {
// Called to process population of AccessibilityEvents.
        return super.dispatchPopulateAccessibilityEvent(event)
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
// Called to process touch screen events.
        return super.dispatchTouchEvent(ev)
    }
    override fun dispatchTrackballEvent(ev: MotionEvent): Boolean {
// Called to process trackball events.
        return super.dispatchTrackballEvent(ev)
    }
    override fun dump(prefix: String, fd: FileDescriptor, writer: PrintWriter, args: Array<String>) {
// Print the Activity's state into the given stream.
        super.dump(prefix, fd, writer, args)
    }
    override fun enterPictureInPictureMode() {
// Puts the activity in picture-in-picture mode.
        super.enterPictureInPictureMode()
    }
    override fun finish() {
// Call this when your activity is done and should be closed.
        super.finish()
    }
    override fun finishActivity(requestCode: Int) {
// Force finish another activity that you had previously started with  startActivityForResult(Intent, int).
        super.finishActivity(requestCode)
    }
    override fun finishActivityFromChild(child: Activity, requestCode: Int) {
// This is called when a child activity of this one calls its  finishActivity().
        super.finishActivityFromChild(child, requestCode)
    }
    override fun finishAffinity() {
// Finish this activity as well as all activities immediately below it  in the current task that have the same affinity.
        super.finishAffinity()
    }
    override fun finishAfterTransition() {
// Reverses the Activity Scene entry Transition and triggers the calling Activity  to reverse its exit Transition.
        super.finishAfterTransition()
    }
    override fun finishAndRemoveTask() {
// Call this when your activity is done and should be closed and the task should be completely  removed as a part of finishing the root activity of the task.
        super.finishAndRemoveTask()
    }
    override fun finishFromChild(child: Activity) {
// This is called when a child activity of this one calls its  finish() method.
        super.finishFromChild(child)
    }
    override fun getActionBar(): ActionBar {
// Retrieve a reference to this activity's ActionBar.
        return super.getActionBar()
    }
    override fun getCallingActivity(): ComponentName {
// Return the name of the activity that invoked this activity.
        return super.getCallingActivity()
    }
    override fun getCallingPackage(): String {
// Return the name of the package that invoked this activity.
        return super.getCallingPackage()
    }
    override fun getChangingConfigurations(): Int {
// If this activity is being destroyed because it can not handle a  configuration parameter being changed (and thus its  onConfigurationChanged(Configuration) method is  not being called), then you can use this method to discover  the set of changes that have occurred while in the process of being  destroyed.
        return super.getChangingConfigurations()
    }
    override fun getComponentName(): ComponentName {
// Returns complete component name of this activity.
        return super.getComponentName()
    }
    override fun getContentScene(): Scene {
// Retrieve the Scene representing this window's current content.
        return super.getContentScene()
    }
    override fun getContentTransitionManager(): TransitionManager {
// Retrieve the TransitionManager responsible for default transitions in this window.
        return super.getContentTransitionManager()
    }
    override fun getCurrentFocus(): View {
// Calls getCurrentFocus() on the  Window of this Activity to return the currently focused view.
        return super.getCurrentFocus()
    }
    override fun getFragmentManager(): FragmentManager {
// Return the FragmentManager for interacting with fragments associated  with this activity.
        return super.getFragmentManager()
    }
    override fun getIntent(): Intent {
// Return the intent that started this activity.
        return super.getIntent()
    }
    override fun getLastNonConfigurationInstance(): Any {
// Retrieve the non-configuration instance data that was previously  returned by onRetainNonConfigurationInstance().
        return super.getLastNonConfigurationInstance()
    }
    override fun getLayoutInflater(): LayoutInflater {
// Convenience for calling  getLayoutInflater().
        return super.getLayoutInflater()
    }
    override fun getLoaderManager(): LoaderManager {
// Return the LoaderManager for this activity, creating it if needed.
        return super.getLoaderManager()
    }
    override fun getLocalClassName(): String {
// Returns class name for this activity with the package prefix removed.
        return super.getLocalClassName()
    }
    override fun getMenuInflater(): MenuInflater {
// Returns a MenuInflater with this context.
        return super.getMenuInflater()
    }
    override fun getParentActivityIntent(): Intent {
// Obtain an Intent that will launch an explicit target activity specified by  this activity's logical parent.
        return super.getParentActivityIntent()
    }
    override fun getPreferences(mode: Int): SharedPreferences {
// Retrieve a SharedPreferences object for accessing preferences  that are private to this activity.
        return super.getPreferences(mode)
    }
    override fun getReferrer(): Uri {
// Return information about who launched this activity.
        return super.getReferrer()
    }
    override fun getRequestedOrientation(): Int {
// Return the current requested orientation of the activity.
        return super.getRequestedOrientation()
    }
    override fun getSystemService(name: String): Any {
// Return the handle to a system-level service by name.
        return super.getSystemService(name)
    }
    override fun getTaskId(): Int {
// Return the identifier of the task this activity is in.
        return super.getTaskId()
    }
    override fun getVoiceInteractor(): VoiceInteractor {
// Retrieve the active VoiceInteractor that the user is going through to  interact with this activity.
        return super.getVoiceInteractor()
    }
    override fun getWindow(): Window {
// Retrieve the current Window for the activity.
        return super.getWindow()
    }
    override fun getWindowManager(): WindowManager {
// Retrieve the window manager for showing custom windows.
        return super.getWindowManager()
    }
    override fun hasWindowFocus(): Boolean {
// Returns true if this activity's main window currently has window focus.
        return super.hasWindowFocus()
    }
    override fun invalidateOptionsMenu() {
// Declare that the options menu has changed, so should be recreated.
        super.invalidateOptionsMenu()
    }
    override fun isChangingConfigurations(): Boolean {
// Check to see whether this activity is in the process of being destroyed in order to be  recreated with a new configuration.
        return super.isChangingConfigurations()
    }
    override fun isDestroyed(): Boolean {
// Returns true if the final onDestroy() call has been made  on the Activity, so this instance is now dead.
        return super.isDestroyed()
    }
    override fun isFinishing(): Boolean {
// Check to see whether this activity is in the process of finishing,  either because you called finish() on it or someone else  has requested that it finished.
        return super.isFinishing()
    }
    override fun isImmersive(): Boolean {
// Bit indicating that this activity is "immersive" and should not be  interrupted by notifications if possible.
        return super.isImmersive()
    }
    override fun isInMultiWindowMode(): Boolean {
// Returns true if the activity is currently in multi-window mode.
        return super.isInMultiWindowMode()
    }
    override fun isInPictureInPictureMode(): Boolean {
// Returns true if the activity is currently in picture-in-picture mode.
        return super.isInPictureInPictureMode()
    }
    override fun isLocalVoiceInteractionSupported(): Boolean {
// Queries whether the currently enabled voice interaction service supports returning  a voice interactor for use by the activity.
        return super.isLocalVoiceInteractionSupported()
    }
    override fun isTaskRoot(): Boolean {
// Return whether this activity is the root of a task.
        return super.isTaskRoot()
    }
    override fun isVoiceInteraction(): Boolean {
// Check whether this activity is running as part of a voice interaction with the user.
        return super.isVoiceInteraction()
    }
    override fun isVoiceInteractionRoot(): Boolean {
// Like isVoiceInteraction(), but only returns true if this is also the root  of a voice interaction.
        return super.isVoiceInteractionRoot()
    }
    override fun moveTaskToBack(nonRoot: Boolean): Boolean {
// Move the task containing this activity to the back of the activity  stack.
        return super.moveTaskToBack(nonRoot)
    }
    override fun navigateUpTo(upIntent: Intent): Boolean {
// Navigate from this activity to the activity specified by upIntent, finishing this activity  in the process.
        return super.navigateUpTo(upIntent)
    }
    override fun navigateUpToFromChild(child: Activity, upIntent: Intent): Boolean {
// This is called when a child activity of this one calls its  navigateUpTo(Intent) method.
        return super.navigateUpToFromChild(child, upIntent)
    }
    override fun onActionModeFinished(mode: ActionMode) {
// Notifies the activity that an action mode has finished.
        super.onActionModeFinished(mode)
    }
    override fun onActionModeStarted(mode: ActionMode) {
// Notifies the Activity that an action mode has been started.
        super.onActionModeStarted(mode)
    }
    override fun onActivityReenter(resultCode: Int, data: Intent) {
// Called when an activity you launched with an activity transition exposes this  Activity through a returning activity transition, giving you the resultCode  and any additional data from it.
        super.onActivityReenter(resultCode, data)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
// Called when an activity you launched exits, giving you the requestCode  you started it with, the resultCode it returned, and any additional  data from it.
        super.onActivityResult(requestCode, resultCode, data)
    }
    override fun onApplyThemeResource(theme: Resources.Theme, resid: Int, first: Boolean) {
// Called by setTheme(int) and getTheme() to apply a theme  resource to the current Theme object.
        super.onApplyThemeResource(theme, resid, first)
    }
    override fun onAttachedToWindow() {
// Called when the main window associated with the activity has been  attached to the window manager.
        super.onAttachedToWindow()
    }
    override fun onAttachFragment(fragment: Fragment) {
// Called when a Fragment is being attached to this activity, immediately  after the call to its Fragment.onAttach()  method and before Fragment.onCreate().
        super.onAttachFragment(fragment)
    }
    override fun onBackPressed() {
// Called when the activity has detected the user's press of the back  key.
        super.onBackPressed()
    }
    override fun onChildTitleChanged(childActivity: Activity, title: CharSequence) {
//
        super.onChildTitleChanged(childActivity, title)
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
// Called by the system when the device configuration changes while your  activity is running.
        super.onConfigurationChanged(newConfig)
    }
    override fun onContentChanged() {
// This hook is called whenever the content view of the screen changes  (due to a call to  Window.setContentView or  Window.addContentView).
        super.onContentChanged()
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
// This hook is called whenever an item in a context menu is selected.
        return super.onContextItemSelected(item)
    }
    override fun onContextMenuClosed(menu: Menu) {
// This hook is called whenever the context menu is being closed (either by  the user canceling the menu with the back/menu button, or when an item is  selected).
        super.onContextMenuClosed(menu)
    }
    override fun onCreate(savedInstanceState: Bundle, persistentState: PersistableBundle) {
// Same as onCreate(android.os.Bundle) but called for those activities created with  the attribute persistableMode set to  persistAcrossReboots.
        super.onCreate(savedInstanceState, persistentState)
    }
    override fun onCreate(savedInstanceState: Bundle) {
// Called when the activity is starting.
        super.onCreate(savedInstanceState)
    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
// Called when a context menu for the view is about to be shown.
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    override fun onCreateDescription(): CharSequence {
// Generate a new description for this activity.
        return super.onCreateDescription()
    }
    override fun onCreateDialog(id: Int): Dialog {
// This method was deprecated       in API level 8.     Old no-arguments version of onCreateDialog(int, Bundle).
        return super.onCreateDialog(id)
    }
    override fun onCreateDialog(id: Int, args: Bundle): Dialog {
// This method was deprecated       in API level 13.     Use the new DialogFragment class with  FragmentManager instead; this is also  available on older platforms through the Android compatibility package.
        return super.onCreateDialog(id, args)
    }
    override fun onCreateNavigateUpTaskStack(builder: TaskStackBuilder) {
// Define the synthetic task stack that will be generated during Up navigation from  a different task.
        super.onCreateNavigateUpTaskStack(builder)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
// Initialize the contents of the Activity's standard options menu.
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
// Default implementation of  onCreatePanelMenu(int, Menu)  for activities.
        return super.onCreatePanelMenu(featureId, menu)
    }
    override fun onCreatePanelView(featureId: Int): View {
// Default implementation of  onCreatePanelView(int)  for activities.
        return super.onCreatePanelView(featureId)
    }
    override fun onCreateThumbnail(outBitmap: Bitmap, canvas: Canvas): Boolean {
// Generate a new thumbnail for this activity.
        return super.onCreateThumbnail(outBitmap, canvas)
    }
    override fun onCreateView(parent: View, name: String, context: Context, attrs: AttributeSet): View {
// Standard implementation of  onCreateView(View, String, Context, AttributeSet)  used when inflating with the LayoutInflater returned by getSystemService(Class).
        return super.onCreateView(parent, name, context, attrs)
    }
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View {
// Standard implementation of  onCreateView(String, Context, AttributeSet) used when  inflating with the LayoutInflater returned by getSystemService(Class).
        return super.onCreateView(name, context, attrs)
    }
    override fun onDestroy() {
// Perform any final cleanup before an activity is destroyed.
        super.onDestroy()
    }
    override fun onDetachedFromWindow() {
// Called when the main window associated with the activity has been  detached from the window manager.
        super.onDetachedFromWindow()
    }
    override fun onEnterAnimationComplete() {
// Activities cannot draw during the period that their windows are animating in.
        super.onEnterAnimationComplete()
    }
    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
// Called when a generic motion event was not handled by any of the  views inside of the activity.
        return super.onGenericMotionEvent(event)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
// Called when a key was pressed down and not handled by any of the views  inside of the activity.
        return super.onKeyDown(keyCode, event)
    }
    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyLongPress(): always returns false (doesn't handle  the event).
        return super.onKeyLongPress(keyCode, event)
    }
    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyMultiple(): always returns false (doesn't handle  the event).
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }
    override fun onKeyShortcut(keyCode: Int, event: KeyEvent): Boolean {
// Called when a key shortcut event is not handled by any of the views in the Activity.
        return super.onKeyShortcut(keyCode, event)
    }
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
// Called when a key was released and not handled by any of the views  inside of the activity.
        return super.onKeyUp(keyCode, event)
    }
    override fun onLocalVoiceInteractionStarted() {
// Callback to indicate that startLocalVoiceInteraction(Bundle) has resulted in a  voice interaction session being started.
        super.onLocalVoiceInteractionStarted()
    }
    override fun onLocalVoiceInteractionStopped() {
// Callback to indicate that the local voice interaction has stopped either  because it was requested through a call to stopLocalVoiceInteraction()  or because it was canceled by the user.
        super.onLocalVoiceInteractionStopped()
    }
    override fun onLowMemory() {
// This is called when the overall system is running low on memory, and  actively running processes should trim their memory usage.
        super.onLowMemory()
    }
    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
// Default implementation of  onMenuItemSelected(int, MenuItem)  for activities.
        return super.onMenuItemSelected(featureId, item)
    }
    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
// Called when a panel's menu is opened by the user.
        return super.onMenuOpened(featureId, menu)
    }
    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
// Called by the system when the activity changes from fullscreen mode to multi-window mode and  visa-versa.
        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }
    override fun onNavigateUp(): Boolean {
// This method is called whenever the user chooses to navigate Up within your application's  activity hierarchy from the action bar.
        return super.onNavigateUp()
    }
    override fun onNavigateUpFromChild(child: Activity): Boolean {
// This is called when a child activity of this one attempts to navigate up.
        return super.onNavigateUpFromChild(child)
    }
    override fun onNewIntent(intent: Intent) {
// This is called for activities that set launchMode to "singleTop" in  their package, or if a client used the FLAG_ACTIVITY_SINGLE_TOP  flag when calling startActivity(Intent).
        super.onNewIntent(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
// This hook is called whenever an item in your options menu is selected.
        return super.onOptionsItemSelected(item)
    }
    override fun onOptionsMenuClosed(menu: Menu) {
// This hook is called whenever the options menu is being closed (either by the user canceling  the menu with the back/menu button, or when an item is selected).
        super.onOptionsMenuClosed(menu)
    }
    override fun onPanelClosed(featureId: Int, menu: Menu) {
// Default implementation of  onPanelClosed(int, Menu) for  activities.
        super.onPanelClosed(featureId, menu)
    }
    override fun onPause() {
// Called as part of the activity lifecycle when an activity is going into  the background, but has not (yet) been killed.
        super.onPause()
    }
    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
// Called by the system when the activity changes to and from picture-in-picture mode.
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
    }
    override fun onPostCreate(savedInstanceState: Bundle, persistentState: PersistableBundle) {
// This is the same as onPostCreate(Bundle) but is called for activities  created with the attribute persistableMode set to  persistAcrossReboots.
        super.onPostCreate(savedInstanceState, persistentState)
    }
    override fun onPostCreate(savedInstanceState: Bundle) {
// Called when activity start-up is complete (after onStart()  and onRestoreInstanceState(Bundle) have been called).
        super.onPostCreate(savedInstanceState)
    }
    override fun onPostResume() {
// Called when activity resume is complete (after onResume() has  been called).
        super.onPostResume()
    }
    override fun onPrepareDialog(id: Int, dialog: Dialog, args: Bundle) {
// This method was deprecated       in API level 13.     Use the new DialogFragment class with  FragmentManager instead; this is also  available on older platforms through the Android compatibility package.
        super.onPrepareDialog(id, dialog, args)
    }
    override fun onPrepareDialog(id: Int, dialog: Dialog) {
// This method was deprecated       in API level 8.     Old no-arguments version of  onPrepareDialog(int, Dialog, Bundle).
        super.onPrepareDialog(id, dialog)
    }
    override fun onPrepareNavigateUpTaskStack(builder: TaskStackBuilder) {
// Prepare the synthetic task stack that will be generated during Up navigation  from a different task.
        super.onPrepareNavigateUpTaskStack(builder)
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
// Prepare the Screen's standard options menu to be displayed.
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onPreparePanel(featureId: Int, view: View, menu: Menu): Boolean {
// Default implementation of  onPreparePanel(int, View, Menu)  for activities.
        return super.onPreparePanel(featureId, view, menu)
    }
    override fun onProvideAssistContent(outContent: AssistContent) {
// This is called when the user is requesting an assist, to provide references  to content related to the current activity.
        super.onProvideAssistContent(outContent)
    }
    override fun onProvideAssistData(data: Bundle) {
// This is called when the user is requesting an assist, to build a full  ACTION_ASSIST Intent with all of the context of the current  application.
        super.onProvideAssistData(data)
    }
    override fun onProvideKeyboardShortcuts(data: List<KeyboardShortcutGroup>, menu: Menu, deviceId: Int) {
// Called when Keyboard Shortcuts are requested for the current window.
        super.onProvideKeyboardShortcuts(data, menu, deviceId)
    }
    override fun onProvideReferrer(): Uri {
// Override to generate the desired referrer for the content currently being shown  by the app.
        return super.onProvideReferrer()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
// Callback for the result from requesting permissions.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onRestart() {
// Called after onStop() when the current activity is being  re-displayed to the user (the user has navigated back to it).
        super.onRestart()
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle, persistentState: PersistableBundle) {
// This is the same as onRestoreInstanceState(Bundle) but is called for activities  created with the attribute persistableMode set to  persistAcrossReboots.
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
// This method is called after onStart() when the activity is  being re-initialized from a previously saved state, given here in  savedInstanceState.
        super.onRestoreInstanceState(savedInstanceState)
    }
    override fun onResume() {
// Called after onRestoreInstanceState(Bundle), onRestart(), or  onPause(), for your activity to start interacting with the user.
        super.onResume()
    }
    override fun onRetainNonConfigurationInstance(): Any {
// Called by the system, as part of destroying an  activity due to a configuration change, when it is known that a new  instance will immediately be created for the new configuration.
        return super.onRetainNonConfigurationInstance()
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
// This is the same as onSaveInstanceState(Bundle) but is called for activities  created with the attribute persistableMode set to  persistAcrossReboots.
        super.onSaveInstanceState(outState, outPersistentState)
    }
    override fun onSaveInstanceState(outState: Bundle) {
// Called to retrieve per-instance state from an activity before being killed  so that the state can be restored in onCreate(Bundle) or  onRestoreInstanceState(Bundle) (the Bundle populated by this method  will be passed to both).
        super.onSaveInstanceState(outState)
    }
    override fun onSearchRequested(searchEvent: SearchEvent): Boolean {
// This hook is called when the user signals the desire to start a search.
        return super.onSearchRequested(searchEvent)
    }
    override fun onSearchRequested(): Boolean {
// Called when the user signals the desire to start a search.
        return super.onSearchRequested()
    }
    override fun onStart() {
// Called after onCreate(Bundle) — or after onRestart() when  the activity had been stopped, but is now again being displayed to the  user.
        super.onStart()
    }
    override fun onStateNotSaved() {
// Called when an onResume() is coming up, prior to other pre-resume callbacks  such as onNewIntent(Intent) and onActivityResult(int, int, Intent).
        super.onStateNotSaved()
    }
    override fun onStop() {
// Called when you are no longer visible to the user.
        super.onStop()
    }
    override fun onTitleChanged(title: CharSequence, color: Int) {
//
        super.onTitleChanged(title, color)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
// Called when a touch screen event was not handled by any of the views  under it.
        return super.onTouchEvent(event)
    }
    override fun onTrackballEvent(event: MotionEvent): Boolean {
// Called when the trackball was moved and not handled by any of the  views inside of the activity.
        return super.onTrackballEvent(event)
    }
    override fun onTrimMemory(level: Int) {
// Called when the operating system has determined that it is a good  time for a process to trim unneeded memory from its process.
        super.onTrimMemory(level)
    }
    override fun onUserInteraction() {
// Called whenever a key, touch, or trackball event is dispatched to the  activity.
        super.onUserInteraction()
    }
    override fun onUserLeaveHint() {
// Called as part of the activity lifecycle when an activity is about to go  into the background as the result of user choice.
        super.onUserLeaveHint()
    }
    override fun onVisibleBehindCanceled() {
// Called when a translucent activity over this activity is becoming opaque or another  activity is being launched.
        super.onVisibleBehindCanceled()
    }
    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams) {
// This is called whenever the current window attributes change.
        super.onWindowAttributesChanged(params)
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
// Called when the current Window of the activity gains or loses  focus.
        super.onWindowFocusChanged(hasFocus)
    }
    override fun onWindowStartingActionMode(callback: ActionMode.Callback, type: Int): ActionMode {
// Called when an action mode is being started for this window.
        return super.onWindowStartingActionMode(callback, type)
    }
    override fun onWindowStartingActionMode(callback: ActionMode.Callback): ActionMode {
// Give the Activity a chance to control the UI for an action mode requested  by the system.
        return super.onWindowStartingActionMode(callback)
    }
    override fun openContextMenu(view: View) {
// Programmatically opens the context menu for a particular view.
        super.openContextMenu(view)
    }
    override fun openOptionsMenu() {
// Programmatically opens the options menu.
        super.openOptionsMenu()
    }
    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
// Call immediately after one of the flavors of startActivity(Intent)  or finish() to specify an explicit transition animation to  perform next.
        super.overridePendingTransition(enterAnim, exitAnim)
    }
    override fun postponeEnterTransition() {
// Postpone the entering activity transition when Activity was started with  makeSceneTransitionAnimation(Activity, android.util.Pair[]).
        super.postponeEnterTransition()
    }
    override fun recreate() {
// Cause this Activity to be recreated with a new instance.
        super.recreate()
    }
    override fun registerForContextMenu(view: View) {
// Registers a context menu to be shown for the given view (multiple views  can show the context menu).
        super.registerForContextMenu(view)
    }
    override fun releaseInstance(): Boolean {
// Ask that the local app instance of this activity be released to free up its memory.
        return super.releaseInstance()
    }
    override fun reportFullyDrawn() {
// Report to the system that your app is now fully drawn, purely for diagnostic  purposes (calling it does not impact the visible behavior of the activity).
        super.reportFullyDrawn()
    }
    override fun requestDragAndDropPermissions(event: DragEvent): DragAndDropPermissions {
// Create DragAndDropPermissions object bound to this activity and controlling the  access permissions for content URIs associated with the DragEvent.
        return super.requestDragAndDropPermissions(event)
    }
    override fun requestVisibleBehind(visible: Boolean): Boolean {
// Activities that want to remain visible behind a translucent activity above them must call  this method anytime between the start of onResume() and the return from  onPause().
        return super.requestVisibleBehind(visible)
    }
    override fun setActionBar(toolbar: Toolbar) {
// Set a Toolbar to act as the ActionBar for this  Activity window.
        super.setActionBar(toolbar)
    }
    override fun setContentTransitionManager(tm: TransitionManager) {
// Set the TransitionManager to use for default transitions in this window.
        super.setContentTransitionManager(tm)
    }
    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
// Set the activity content to an explicit view.
        super.setContentView(view, params)
    }
    override fun setContentView(view: View) {
// Set the activity content to an explicit view.
        super.setContentView(view)
    }
    override fun setContentView(layoutResID: Int) {
// Set the activity content from a layout resource.
        super.setContentView(layoutResID)
    }
    override fun setEnterSharedElementCallback(callback: SharedElementCallback) {
// When makeSceneTransitionAnimation(Activity, android.view.View, String) was used to start an Activity, callback  will be called to handle shared elements on the launched Activity.
        super.setEnterSharedElementCallback(callback)
    }
    override fun setExitSharedElementCallback(callback: SharedElementCallback) {
// When makeSceneTransitionAnimation(Activity, android.view.View, String) was used to start an Activity, callback  will be called to handle shared elements on the launching Activity.
        super.setExitSharedElementCallback(callback)
    }
    override fun setFinishOnTouchOutside(finish: Boolean) {
// Sets whether this activity is finished when touched outside its window's  bounds.
        super.setFinishOnTouchOutside(finish)
    }
    override fun setImmersive(i: Boolean) {
// Adjust the current immersive mode setting.
        super.setImmersive(i)
    }
    override fun setIntent(newIntent: Intent) {
// Change the intent returned by getIntent().
        super.setIntent(newIntent)
    }
    override fun setRequestedOrientation(requestedOrientation: Int) {
// Change the desired orientation of this activity.
        super.setRequestedOrientation(requestedOrientation)
    }
    override fun setTaskDescription(taskDescription: ActivityManager.TaskDescription) {
// Sets information describing the task with this activity for presentation inside the Recents  System UI.
        super.setTaskDescription(taskDescription)
    }
    override fun setTheme(resid: Int) {
// Set the base theme for this context.
        super.setTheme(resid)
    }
    override fun setTitle(title: CharSequence) {
// Change the title associated with this activity.
        super.setTitle(title)
    }
    override fun setTitle(titleId: Int) {
// Change the title associated with this activity.
        super.setTitle(titleId)
    }
    override fun setTitleColor(textColor: Int) {
// This method was deprecated       in API level 21.     Use action bar styles instead.
        super.setTitleColor(textColor)
    }
    override fun setVisible(visible: Boolean) {
// Control whether this activity's main window is visible.
        super.setVisible(visible)
    }
    override fun setVrModeEnabled(enabled: Boolean, requestedComponent: ComponentName) {
// Enable or disable virtual reality (VR) mode for this Activity.
        super.setVrModeEnabled(enabled, requestedComponent)
    }
    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
// Gets whether you should show UI with rationale for requesting a permission.
        return super.shouldShowRequestPermissionRationale(permission)
    }
    override fun shouldUpRecreateTask(targetIntent: Intent): Boolean {
// Returns true if the app should recreate the task when navigating 'up' from this activity  by using targetIntent.
        return super.shouldUpRecreateTask(targetIntent)
    }
    override fun showAssist(args: Bundle): Boolean {
// Ask to have the current assistant shown to the user.
        return super.showAssist(args)
    }
    override fun showLockTaskEscapeMessage() {
// Shows the user the system defined message for telling the user how to exit  lock task mode.
        super.showLockTaskEscapeMessage()
    }
    override fun startActionMode(callback: ActionMode.Callback, type: Int): ActionMode {
// Start an action mode of the given type.
        return super.startActionMode(callback, type)
    }
    override fun startActionMode(callback: ActionMode.Callback): ActionMode {
// Start an action mode of the default type TYPE_PRIMARY.
        return super.startActionMode(callback)
    }
    override fun startActivities(intents: Array<Intent>, options: Bundle) {
// Launch a new activity.
        super.startActivities(intents, options)
    }
    override fun startActivities(intents: Array<Intent>) {
// Same as startActivities(Intent[], Bundle) with no options  specified.
        super.startActivities(intents)
    }
    override fun startActivity(intent: Intent) {
// Same as startActivity(Intent, Bundle) with no options  specified.
        super.startActivity(intent)
    }
    override fun startActivity(intent: Intent, options: Bundle) {
// Launch a new activity.
        super.startActivity(intent, options)
    }
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
// Same as calling startActivityForResult(Intent, int, Bundle)  with no options.
        super.startActivityForResult(intent, requestCode)
    }
    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle) {
// Launch an activity for which you would like a result when it finished.
        super.startActivityForResult(intent, requestCode, options)
    }
    override fun startActivityFromChild(child: Activity, intent: Intent, requestCode: Int) {
// Same as calling startActivityFromChild(Activity, Intent, int, Bundle)  with no options.
        super.startActivityFromChild(child, intent, requestCode)
    }
    override fun startActivityFromChild(child: Activity, intent: Intent, requestCode: Int, options: Bundle) {
// This is called when a child activity of this one calls its  startActivity(Intent) or startActivityForResult(Intent, int) method.
        super.startActivityFromChild(child, intent, requestCode, options)
    }
    override fun startActivityFromFragment(fragment: Fragment, intent: Intent, requestCode: Int, options: Bundle) {
// This is called when a Fragment in this activity calls its  startActivity(Intent) or startActivityForResult(Intent, int)  method.
        super.startActivityFromFragment(fragment, intent, requestCode, options)
    }
    override fun startActivityFromFragment(fragment: Fragment, intent: Intent, requestCode: Int) {
// Same as calling startActivityFromFragment(Fragment, Intent, int, Bundle)  with no options.
        super.startActivityFromFragment(fragment, intent, requestCode)
    }
    override fun startActivityIfNeeded(intent: Intent, requestCode: Int, options: Bundle): Boolean {
// A special variation to launch an activity only if a new activity  instance is needed to handle the given Intent.
        return super.startActivityIfNeeded(intent, requestCode, options)
    }
    override fun startActivityIfNeeded(intent: Intent, requestCode: Int): Boolean {
// Same as calling startActivityIfNeeded(Intent, int, Bundle)  with no options.
        return super.startActivityIfNeeded(intent, requestCode)
    }
    override fun startIntentSender(
        intent: IntentSender,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int
    ) {
// Same as calling startIntentSender(IntentSender, Intent, int, int, int, Bundle)  with no options.
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags)
    }
    override fun startIntentSender(
        intent: IntentSender,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle
    ) {
// Like startActivity(Intent, Bundle), but taking a IntentSender  to start; see  startIntentSenderForResult(IntentSender, int, Intent, int, int, int, Bundle)  for more information.
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options)
    }
    override fun startIntentSenderForResult(
        intent: IntentSender,
        requestCode: Int,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int
    ) {
// Same as calling startIntentSenderForResult(IntentSender, int, Intent, int, int, int, Bundle) with no options.
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags)
    }
    override fun startIntentSenderForResult(
        intent: IntentSender,
        requestCode: Int,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle
    ) {
// Like startActivityForResult(Intent, int), but allowing you  to use a IntentSender to describe the activity to be started.
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)
    }
    override fun startIntentSenderFromChild(
        child: Activity,
        intent: IntentSender,
        requestCode: Int,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle
    ) {
// Like startActivityFromChild(Activity, Intent, int), but  taking a IntentSender; see  startIntentSenderForResult(IntentSender, int, Intent, int, int, int)  for more information.
        super.startIntentSenderFromChild(
            child,
            intent,
            requestCode,
            fillInIntent,
            flagsMask,
            flagsValues,
            extraFlags,
            options
        )
    }
    override fun startIntentSenderFromChild(
        child: Activity,
        intent: IntentSender,
        requestCode: Int,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int
    ) {
// Same as calling startIntentSenderFromChild(Activity, IntentSender, int, Intent, int, int, int, Bundle) with no options.
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags)
    }
    override fun startLocalVoiceInteraction(privateOptions: Bundle) {
// Starts a local voice interaction session.
        super.startLocalVoiceInteraction(privateOptions)
    }
    override fun startLockTask() {
// Request to put this Activity in a mode where the user is locked to the  current task.
        super.startLockTask()
    }
    override fun startManagingCursor(c: Cursor) {
// This method was deprecated       in API level 11.     Use the new CursorLoader class with  LoaderManager instead; this is also  available on older platforms through the Android compatibility package.
        super.startManagingCursor(c)
    }
    override fun startNextMatchingActivity(intent: Intent, options: Bundle): Boolean {
// Special version of starting an activity, for use when you are replacing  other activity components.
        return super.startNextMatchingActivity(intent, options)
    }
    override fun startNextMatchingActivity(intent: Intent): Boolean {
// Same as calling startNextMatchingActivity(Intent, Bundle) with  no options.
        return super.startNextMatchingActivity(intent)
    }
    override fun startPostponedEnterTransition() {
// Begin postponed transitions after postponeEnterTransition() was called.
        super.startPostponedEnterTransition()
    }
    override fun startSearch(
        initialQuery: String,
        selectInitialQuery: Boolean,
        appSearchData: Bundle,
        globalSearch: Boolean
    ) {
// This hook is called to launch the search UI.
        super.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch)
    }
    override fun stopLocalVoiceInteraction() {
// Request to terminate the current voice interaction that was previously started  using startLocalVoiceInteraction(Bundle).
        super.stopLocalVoiceInteraction()
    }
    override fun stopLockTask() {
// Allow the user to switch away from the current task.
        super.stopLockTask()
    }
    override fun stopManagingCursor(c: Cursor) {
// This method was deprecated       in API level 11.     Use the new CursorLoader class with  LoaderManager instead; this is also  available on older platforms through the Android compatibility package.
        super.stopManagingCursor(c)
    }
    override fun takeKeyEvents(get: Boolean) {
// Request that key events come to this activity.
        super.takeKeyEvents(get)
    }
    override fun triggerSearch(query: String, appSearchData: Bundle) {
// Similar to startSearch(String, boolean, Bundle, boolean), but actually fires off the search query after invoking  the search dialog.
        super.triggerSearch(query, appSearchData)
    }
    override fun unregisterForContextMenu(view: View) {
// Prevents a context menu to be shown for the given view.
        super.unregisterForContextMenu(view)
    }
}