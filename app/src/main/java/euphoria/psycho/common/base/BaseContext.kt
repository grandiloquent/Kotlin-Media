package euphoria.psycho.common.base


import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import android.view.Display
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

abstract class BaseContext : Context() {

    override fun bindService(service: Intent, conn: ServiceConnection, flags: Int): Boolean {
// Connect to an application service, creating it if needed.
        TODO("not implemented")
// return super.bindService(service,conn,flags)
    }
    override fun checkCallingOrSelfPermission(permission: String): Int {
// Determine whether the calling process of an IPC or you have been  granted a particular permission.
        TODO("not implemented")
// return super.checkCallingOrSelfPermission(permission)
    }
    override fun checkCallingOrSelfUriPermission(uri: Uri, modeFlags: Int): Int {
// Determine whether the calling process of an IPC or you has been granted  permission to access a specific URI.
        TODO("not implemented")
// return super.checkCallingOrSelfUriPermission(uri,modeFlags)
    }
    override fun checkCallingPermission(permission: String): Int {
// Determine whether the calling process of an IPC you are handling has been  granted a particular permission.
        TODO("not implemented")
// return super.checkCallingPermission(permission)
    }
    override fun checkCallingUriPermission(uri: Uri, modeFlags: Int): Int {
// Determine whether the calling process and user ID has been  granted permission to access a specific URI.
        TODO("not implemented")
// return super.checkCallingUriPermission(uri,modeFlags)
    }
    override fun checkPermission(permission: String, pid: Int, uid: Int): Int {
// Determine whether the given permission is allowed for a particular  process and user ID running in the system.
        TODO("not implemented")
// return super.checkPermission(permission,pid,uid)
    }
    override fun checkSelfPermission(permission: String): Int {
// Determine whether you have been granted a particular permission.
        TODO("not implemented")
// return super.checkSelfPermission(permission)
    }
    override fun checkUriPermission(
        uri: Uri,
        readPermission: String,
        writePermission: String,
        pid: Int,
        uid: Int,
        modeFlags: Int
    ): Int {
// Check both a Uri and normal permission.
        TODO("not implemented")
// return super.checkUriPermission(uri,readPermission,writePermission,pid,uid,modeFlags)
    }
    override fun checkUriPermission(uri: Uri, pid: Int, uid: Int, modeFlags: Int): Int {
// Determine whether a particular process and user ID has been granted  permission to access a specific URI.
        TODO("not implemented")
// return super.checkUriPermission(uri,pid,uid,modeFlags)
    }
    override fun clearWallpaper() {
// This method was deprecated       in API level 5.     Use WallpaperManager.clear() instead.
        TODO("not implemented")
// super.clearWallpaper()
    }
    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
// Return a new Context object for the current Context but whose resources  are adjusted to match the given Configuration.
        TODO("not implemented")
// return super.createConfigurationContext(overrideConfiguration)
    }
    override fun createDeviceProtectedStorageContext(): Context {
// Return a new Context object for the current Context but whose storage  APIs are backed by device-protected storage.
        TODO("not implemented")
// return super.createDeviceProtectedStorageContext()
    }
    override fun createDisplayContext(display: Display): Context {
// Return a new Context object for the current Context but whose resources  are adjusted to match the metrics of the given Display.
        TODO("not implemented")
// return super.createDisplayContext(display)
    }
    override fun createPackageContext(packageName: String, flags: Int): Context {
// Return a new Context object for the given application name.
        TODO("not implemented")
// return super.createPackageContext(packageName,flags)
    }
    override fun databaseList(): Array<String> {
// Returns an array of strings naming the private databases associated with  this Context's application package.
        TODO("not implemented")
// return super.databaseList()
    }
    override fun deleteDatabase(name: String): Boolean {
// Delete an existing private SQLiteDatabase associated with this Context's  application package.
        TODO("not implemented")
// return super.deleteDatabase(name)
    }
    override fun deleteFile(name: String): Boolean {
// Delete the given private file associated with this Context's  application package.
        TODO("not implemented")
// return super.deleteFile(name)
    }
    override fun deleteSharedPreferences(name: String): Boolean {
// Delete an existing shared preferences file.
        TODO("not implemented")
// return super.deleteSharedPreferences(name)
    }
    override fun enforceCallingOrSelfPermission(permission: String, message: String) {
// If neither you nor the calling process of an IPC you are  handling has been granted a particular permission, throw a  SecurityException.
        TODO("not implemented")
// super.enforceCallingOrSelfPermission(permission,message)
    }
    override fun enforceCallingOrSelfUriPermission(uri: Uri, modeFlags: Int, message: String) {
// If the calling process of an IPC or you has not been  granted permission to access a specific URI, throw SecurityException.
        TODO("not implemented")
// super.enforceCallingOrSelfUriPermission(uri,modeFlags,message)
    }
    override fun enforceCallingPermission(permission: String, message: String) {
// If the calling process of an IPC you are handling has not been  granted a particular permission, throw a SecurityException.
        TODO("not implemented")
// super.enforceCallingPermission(permission,message)
    }
    override fun enforceCallingUriPermission(uri: Uri, modeFlags: Int, message: String) {
// If the calling process and user ID has not been granted  permission to access a specific URI, throw SecurityException.
        TODO("not implemented")
// super.enforceCallingUriPermission(uri,modeFlags,message)
    }
    override fun enforcePermission(permission: String, pid: Int, uid: Int, message: String) {
// If the given permission is not allowed for a particular process  and user ID running in the system, throw a SecurityException.
        TODO("not implemented")
// super.enforcePermission(permission,pid,uid,message)
    }
    override fun enforceUriPermission(
        uri: Uri,
        readPermission: String,
        writePermission: String,
        pid: Int,
        uid: Int,
        modeFlags: Int,
        message: String
    ) {
// Enforce both a Uri and normal permission.
        TODO("not implemented")
// super.enforceUriPermission(uri,readPermission,writePermission,pid,uid,modeFlags,message)
    }
    override fun enforceUriPermission(uri: Uri, pid: Int, uid: Int, modeFlags: Int, message: String) {
// If a particular process and user ID has not been granted  permission to access a specific URI, throw SecurityException.
        TODO("not implemented")
// super.enforceUriPermission(uri,pid,uid,modeFlags,message)
    }
    override fun fileList(): Array<String> {
// Returns an array of strings naming the private files associated with  this Context's application package.
        TODO("not implemented")
// return super.fileList()
    }
    override fun getApplicationContext(): Context {
// Return the context of the single, global Application object of the  current process.
        TODO("not implemented")
// return super.getApplicationContext()
    }
    override fun getApplicationInfo(): ApplicationInfo {
// Return the full application info for this context's package.
        TODO("not implemented")
// return super.getApplicationInfo()
    }
    override fun getAssets(): AssetManager {
// Returns an AssetManager instance for the application's package.
        TODO("not implemented")
// return super.getAssets()
    }
    override fun getCacheDir(): File {
// Returns the absolute path to the application specific cache directory on  the filesystem.
        TODO("not implemented")
// return super.getCacheDir()
    }
    override fun getClassLoader(): ClassLoader {
// Return a class loader you can use to retrieve classes in this package.
        TODO("not implemented")
// return super.getClassLoader()
    }
    override fun getCodeCacheDir(): File {
// Returns the absolute path to the application specific cache directory on  the filesystem designed for storing cached code.
        TODO("not implemented")
// return super.getCodeCacheDir()
    }
    override fun getContentResolver(): ContentResolver {
// Return a ContentResolver instance for your application's package.
        TODO("not implemented")
// return super.getContentResolver()
    }
    override fun getDatabasePath(name: String): File {
// Returns the absolute path on the filesystem where a database created with  openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory) is stored.
        TODO("not implemented")
// return super.getDatabasePath(name)
    }
    override fun getDataDir(): File {
// Returns the absolute path to the directory on the filesystem where all  private files belonging to this app are stored.
        TODO("not implemented")
// return super.getDataDir()
    }
    override fun getDir(name: String, mode: Int): File {
// Retrieve, creating if needed, a new directory in which the application  can place its own custom data files.
        TODO("not implemented")
// return super.getDir(name,mode)
    }
    override fun getExternalCacheDir(): File {
// Returns absolute path to application-specific directory on the primary  shared/external storage device where the application can place cache  files it owns.
        TODO("not implemented")
// return super.getExternalCacheDir()
    }
    override fun getExternalCacheDirs(): Array<File> {
// Returns absolute paths to application-specific directories on all  shared/external storage devices where the application can place cache  files it owns.
        TODO("not implemented")
// return super.getExternalCacheDirs()
    }
    override fun getExternalFilesDir(type: String): File {
// Returns the absolute path to the directory on the primary shared/external  storage device where the application can place persistent files it owns.
        TODO("not implemented")
// return super.getExternalFilesDir(type)
    }
    override fun getExternalFilesDirs(type: String): Array<File> {
// Returns absolute paths to application-specific directories on all  shared/external storage devices where the application can place  persistent files it owns.
        TODO("not implemented")
// return super.getExternalFilesDirs(type)
    }
    override fun getExternalMediaDirs(): Array<File> {
// Returns absolute paths to application-specific directories on all  shared/external storage devices where the application can place media  files.
        TODO("not implemented")
// return super.getExternalMediaDirs()
    }
    override fun getFilesDir(): File {
// Returns the absolute path to the directory on the filesystem where files  created with openFileOutput(String, int) are stored.
        TODO("not implemented")
// return super.getFilesDir()
    }
    override fun getFileStreamPath(name: String): File {
// Returns the absolute path on the filesystem where a file created with  openFileOutput(String, int) is stored.
        TODO("not implemented")
// return super.getFileStreamPath(name)
    }
    override fun getMainLooper(): Looper {
// Return the Looper for the main thread of the current process.
        TODO("not implemented")
// return super.getMainLooper()
    }
    override fun getNoBackupFilesDir(): File {
// Returns the absolute path to the directory on the filesystem similar to  getFilesDir().
        TODO("not implemented")
// return super.getNoBackupFilesDir()
    }
    override fun getObbDir(): File {
// Return the primary shared/external storage directory where this  application's OBB files (if there are any) can be found.
        TODO("not implemented")
// return super.getObbDir()
    }
    override fun getObbDirs(): Array<File> {
// Returns absolute paths to application-specific directories on all  shared/external storage devices where the application's OBB files (if  there are any) can be found.
        TODO("not implemented")
// return super.getObbDirs()
    }
    override fun getPackageCodePath(): String {
// Return the full path to this context's primary Android package.
        TODO("not implemented")
// return super.getPackageCodePath()
    }
    override fun getPackageManager(): PackageManager {
// Return PackageManager instance to find global package information.
        TODO("not implemented")
// return super.getPackageManager()
    }
    override fun getPackageName(): String {
// Return the name of this application's package.
        TODO("not implemented")
// return super.getPackageName()
    }
    override fun getPackageResourcePath(): String {
// Return the full path to this context's primary Android package.
        TODO("not implemented")
// return super.getPackageResourcePath()
    }
    override fun getResources(): Resources {
// Returns a Resources instance for the application's package.
        TODO("not implemented")
// return super.getResources()
    }
    override fun getSharedPreferences(name: String, mode: Int): SharedPreferences {
// Retrieve and hold the contents of the preferences file 'name', returning  a SharedPreferences through which you can retrieve and modify its  values.
        TODO("not implemented")
// return super.getSharedPreferences(name,mode)
    }
    override fun getSystemService(name: String): Object {
// Return the handle to a system-level service by name.
        TODO("not implemented")
// return super.getSystemService(name)
    }
    override fun getTheme(): Resources.Theme {
// Return the Theme object associated with this Context.
        TODO("not implemented")
// return super.getTheme()
    }
    override fun getWallpaper(): Drawable {
// This method was deprecated       in API level 5.     Use WallpaperManager.get() instead.
        TODO("not implemented")
// return super.getWallpaper()
    }
    override fun getWallpaperDesiredMinimumHeight(): Int {
// This method was deprecated       in API level 5.     Use WallpaperManager.getDesiredMinimumHeight() instead.
        TODO("not implemented")
// return super.getWallpaperDesiredMinimumHeight()
    }
    override fun getWallpaperDesiredMinimumWidth(): Int {
// This method was deprecated       in API level 5.     Use WallpaperManager.getDesiredMinimumWidth() instead.
        TODO("not implemented")
// return super.getWallpaperDesiredMinimumWidth()
    }
    override fun grantUriPermission(toPackage: String, uri: Uri, modeFlags: Int) {
// Grant permission to access a specific Uri to another package, regardless  of whether that package has general permission to access the Uri's  content provider.
        TODO("not implemented")
// super.grantUriPermission(toPackage,uri,modeFlags)
    }
    override fun isDeviceProtectedStorage(): Boolean {
// Indicates if the storage APIs of this Context are backed by  device-protected storage.
        TODO("not implemented")
// return super.isDeviceProtectedStorage()
    }
    override fun isRestricted(): Boolean {
// Indicates whether this Context is restricted.
        TODO("not implemented")
// return super.isRestricted()
    }
    override fun moveDatabaseFrom(sourceContext: Context, name: String): Boolean {
// Move an existing database file from the given source storage context to  this context.
        TODO("not implemented")
// return super.moveDatabaseFrom(sourceContext,name)
    }
    override fun moveSharedPreferencesFrom(sourceContext: Context, name: String): Boolean {
// Move an existing shared preferences file from the given source storage  context to this context.
        TODO("not implemented")
// return super.moveSharedPreferencesFrom(sourceContext,name)
    }
    override fun openFileInput(name: String): FileInputStream {
// Open a private file associated with this Context's application package  for reading.
        TODO("not implemented")
// return super.openFileInput(name)
    }
    override fun openFileOutput(name: String, mode: Int): FileOutputStream {
// Open a private file associated with this Context's application package  for writing.
        TODO("not implemented")
// return super.openFileOutput(name,mode)
    }
    override fun openOrCreateDatabase(
        name: String,
        mode: Int,
        factory: SQLiteDatabase.CursorFactory,
        errorHandler: DatabaseErrorHandler
    ): SQLiteDatabase {
// Open a new private SQLiteDatabase associated with this Context's  application package.
        TODO("not implemented")
// return super.openOrCreateDatabase(name,mode,factory,errorHandler)
    }
    override fun openOrCreateDatabase(name: String, mode: Int, factory: SQLiteDatabase.CursorFactory): SQLiteDatabase {
// Open a new private SQLiteDatabase associated with this Context's  application package.
        TODO("not implemented")
// return super.openOrCreateDatabase(name,mode,factory)
    }
    override fun peekWallpaper(): Drawable {
// This method was deprecated       in API level 5.     Use WallpaperManager.peek() instead.
        TODO("not implemented")
// return super.peekWallpaper()
    }
    override fun registerComponentCallbacks(callback: ComponentCallbacks) {
// Add a new ComponentCallbacks to the base application of the  Context, which will be called at the same times as the ComponentCallbacks  methods of activities and other components are called.
        TODO("not implemented")
// super.registerComponentCallbacks(callback)
    }
    override fun registerReceiver(receiver: BroadcastReceiver, filter: IntentFilter): Intent {
// Register a BroadcastReceiver to be run in the main activity thread.
        TODO("not implemented")
// return super.registerReceiver(receiver,filter)
    }
    override fun registerReceiver(
        receiver: BroadcastReceiver,
        filter: IntentFilter,
        broadcastPermission: String,
        scheduler: Handler
    ): Intent {
// Register to receive intent broadcasts, to run in the context of  scheduler.
        TODO("not implemented")
// return super.registerReceiver(receiver,filter,broadcastPermission,scheduler)
    }
    override fun removeStickyBroadcast(intent: Intent) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.removeStickyBroadcast(intent)
    }
    override fun removeStickyBroadcastAsUser(intent: Intent, user: UserHandle) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.removeStickyBroadcastAsUser(intent,user)
    }
    override fun revokeUriPermission(uri: Uri, modeFlags: Int) {
// Remove all permissions to access a particular content provider Uri  that were previously added with grantUriPermission(String, Uri, int).
        TODO("not implemented")
// super.revokeUriPermission(uri,modeFlags)
    }
    override fun sendBroadcast(intent: Intent, receiverPermission: String) {
// Broadcast the given intent to all interested BroadcastReceivers, allowing  an optional required permission to be enforced.
        TODO("not implemented")
// super.sendBroadcast(intent,receiverPermission)
    }
    override fun sendBroadcast(intent: Intent) {
// Broadcast the given intent to all interested BroadcastReceivers.
        TODO("not implemented")
// super.sendBroadcast(intent)
    }
    override fun sendBroadcastAsUser(intent: Intent, user: UserHandle) {
// Version of sendBroadcast(Intent) that allows you to specify the  user the broadcast will be sent to.
        TODO("not implemented")
// super.sendBroadcastAsUser(intent,user)
    }
    override fun sendBroadcastAsUser(intent: Intent, user: UserHandle, receiverPermission: String) {
// Version of sendBroadcast(Intent, String) that allows you to specify the  user the broadcast will be sent to.
        TODO("not implemented")
// super.sendBroadcastAsUser(intent,user,receiverPermission)
    }
    override fun sendOrderedBroadcast(
        intent: Intent,
        receiverPermission: String,
        resultReceiver: BroadcastReceiver,
        scheduler: Handler,
        initialCode: Int,
        initialData: String,
        initialExtras: Bundle
    ) {
// Version of sendBroadcast(Intent) that allows you to  receive data back from the broadcast.
        TODO("not implemented")
// super.sendOrderedBroadcast(intent,receiverPermission,resultReceiver,scheduler,initialCode,initialData,initialExtras)
    }
    override fun sendOrderedBroadcast(intent: Intent, receiverPermission: String) {
// Broadcast the given intent to all interested BroadcastReceivers, delivering  them one at a time to allow more preferred receivers to consume the  broadcast before it is delivered to less preferred receivers.
        TODO("not implemented")
// super.sendOrderedBroadcast(intent,receiverPermission)
    }
    override fun sendOrderedBroadcastAsUser(
        intent: Intent,
        user: UserHandle,
        receiverPermission: String,
        resultReceiver: BroadcastReceiver,
        scheduler: Handler,
        initialCode: Int,
        initialData: String,
        initialExtras: Bundle
    ) {
// Version of  sendOrderedBroadcast(Intent, String, BroadcastReceiver, Handler, int, String, Bundle)  that allows you to specify the  user the broadcast will be sent to.
        TODO("not implemented")
// super.sendOrderedBroadcastAsUser(intent,user,receiverPermission,resultReceiver,scheduler,initialCode,initialData,initialExtras)
    }
    override fun sendStickyBroadcast(intent: Intent) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.sendStickyBroadcast(intent)
    }
    override fun sendStickyBroadcastAsUser(intent: Intent, user: UserHandle) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.sendStickyBroadcastAsUser(intent,user)
    }
    override fun sendStickyOrderedBroadcast(
        intent: Intent,
        resultReceiver: BroadcastReceiver,
        scheduler: Handler,
        initialCode: Int,
        initialData: String,
        initialExtras: Bundle
    ) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.sendStickyOrderedBroadcast(intent,resultReceiver,scheduler,initialCode,initialData,initialExtras)
    }
    override fun sendStickyOrderedBroadcastAsUser(
        intent: Intent,
        user: UserHandle,
        resultReceiver: BroadcastReceiver,
        scheduler: Handler,
        initialCode: Int,
        initialData: String,
        initialExtras: Bundle
    ) {
// This method was deprecated       in API level 21.     Sticky broadcasts should not be used.  They provide no security (anyone  can access them), no protection (anyone can modify them), and many other problems.  The recommended pattern is to use a non-sticky broadcast to report that something  has changed, with another mechanism for apps to retrieve the current value whenever  desired.
        TODO("not implemented")
// super.sendStickyOrderedBroadcastAsUser(intent,user,resultReceiver,scheduler,initialCode,initialData,initialExtras)
    }
    override fun setTheme(resid: Int) {
// Set the base theme for this context.
        TODO("not implemented")
// super.setTheme(resid)
    }
    override fun setWallpaper(bitmap: Bitmap) {
// This method was deprecated       in API level 5.     Use WallpaperManager.set() instead.
        TODO("not implemented")
// super.setWallpaper(bitmap)
    }
    override fun setWallpaper(data: InputStream) {
// This method was deprecated       in API level 5.     Use WallpaperManager.set() instead.
        TODO("not implemented")
// super.setWallpaper(data)
    }
    override fun startActivities(intents: Array<Intent>, options: Bundle) {
// Launch multiple new activities.
        TODO("not implemented")
// super.startActivities(intents,options)
    }
    override fun startActivities(intents: Array<Intent>) {
// Same as startActivities(Intent[], Bundle) with no options  specified.
        TODO("not implemented")
// super.startActivities(intents)
    }
    override fun startActivity(intent: Intent) {
// Same as startActivity(Intent, Bundle) with no options  specified.
        TODO("not implemented")
// super.startActivity(intent)
    }
    override fun startActivity(intent: Intent, options: Bundle) {
// Launch a new activity.
        TODO("not implemented")
// super.startActivity(intent,options)
    }
    override fun startInstrumentation(className: ComponentName, profileFile: String, arguments: Bundle): Boolean {
// Start executing an Instrumentation class.
        TODO("not implemented")
// return super.startInstrumentation(className,profileFile,arguments)
    }
    override fun startIntentSender(
        intent: IntentSender,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int
    ) {
// Same as startIntentSender(IntentSender, Intent, int, int, int, Bundle)  with no options specified.
        TODO("not implemented")
// super.startIntentSender(intent,fillInIntent,flagsMask,flagsValues,extraFlags)
    }
    override fun startIntentSender(
        intent: IntentSender,
        fillInIntent: Intent,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle
    ) {
// Like startActivity(Intent, Bundle), but taking a IntentSender  to start.
        TODO("not implemented")
// super.startIntentSender(intent,fillInIntent,flagsMask,flagsValues,extraFlags,options)
    }
    override fun startService(service: Intent): ComponentName {
// Request that a given application service be started.
        TODO("not implemented")
// return super.startService(service)
    }
    override fun stopService(service: Intent): Boolean {
// Request that a given application service be stopped.
        TODO("not implemented")
// return super.stopService(service)
    }
    override fun unbindService(conn: ServiceConnection) {
// Disconnect from an application service.
        TODO("not implemented")
// super.unbindService(conn)
    }
    override fun unregisterComponentCallbacks(callback: ComponentCallbacks) {
// Remove a ComponentCallbacks object that was previously registered  with registerComponentCallbacks(ComponentCallbacks).
        TODO("not implemented")
// super.unregisterComponentCallbacks(callback)
    }
    override fun unregisterReceiver(receiver: BroadcastReceiver) {
// Unregister a previously registered BroadcastReceiver.
        TODO("not implemented")
// super.unregisterReceiver(receiver)
    }
}