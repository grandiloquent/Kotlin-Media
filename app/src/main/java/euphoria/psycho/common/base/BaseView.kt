package euphoria.psycho.common.base


import android.animation.StateListAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Parcelable
import android.util.SparseArray
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityNodeProvider
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection

class BaseView : View {
    constructor(context: Context) : super(context) {

    }
    // override fun addChildrenForAccessibility(outChildren:ArrayList&lt;View&gt;)
// override fun addFocusables(views:ArrayList&lt;View&gt;,direction:Int)
// override fun addFocusables(views:ArrayList&lt;View&gt;,direction:Int,focusableMode:Int)
// override fun addOnAttachStateChangeListener(listener:View.OnAttachStateChangeListener)
// override fun addOnLayoutChangeListener(listener:View.OnLayoutChangeListener)
// override fun addTouchables(views:ArrayList&lt;View&gt;)
// override fun animate():ViewPropertyAnimator
// override fun announceForAccessibility(text:CharSequence)
// override fun bringToFront()
// override fun buildDrawingCache()
// override fun buildDrawingCache(autoScale:Boolean)
// override fun buildLayer()
// override fun callOnClick():Boolean
// override fun cancelDragAndDrop()
// override fun cancelLongPress()
// override fun cancelPendingInputEvents()
// override fun canResolveLayoutDirection():Boolean
// override fun canResolveTextAlignment():Boolean
// override fun canResolveTextDirection():Boolean
// override fun canScrollHorizontally(direction:Int):Boolean
// override fun canScrollVertically(direction:Int):Boolean
// override fun checkInputConnectionProxy(view:View):Boolean
// override fun clearAnimation()
// override fun clearFocus()
// override fun combineMeasuredStates(curState:Int,newState:Int):Int
// override fun computeScroll()
// override fun computeSystemWindowInsets(in:WindowInsets,outLocalInsets:Rect):WindowInsets
// override fun createAccessibilityNodeInfo():AccessibilityNodeInfo
// override fun createContextMenu(menu:ContextMenu)
// override fun destroyDrawingCache()
// override fun dispatchApplyWindowInsets(insets:WindowInsets):WindowInsets
// override fun dispatchConfigurationChanged(newConfig:Configuration)
// override fun dispatchDisplayHint(hint:Int)
// override fun dispatchDragEvent(event:DragEvent):Boolean
// override fun dispatchDrawableHotspotChanged(x:Float,y:Float)
// override fun dispatchFinishTemporaryDetach()
// override fun dispatchGenericMotionEvent(event:MotionEvent):Boolean
// override fun dispatchKeyEvent(event:KeyEvent):Boolean
// override fun dispatchKeyEventPreIme(event:KeyEvent):Boolean
// override fun dispatchKeyShortcutEvent(event:KeyEvent):Boolean
// override fun dispatchNestedFling(velocityX:Float,velocityY:Float,consumed:Boolean):Boolean
// override fun dispatchNestedPreFling(velocityX:Float,velocityY:Float):Boolean
// override fun dispatchNestedPrePerformAccessibilityAction(action:Int,arguments:Bundle):Boolean
// override fun dispatchNestedPreScroll(dx:Int,dy:Int,consumed:Int[],offsetInWindow:Int[]):Boolean
// override fun dispatchNestedScroll(dxConsumed:Int,dyConsumed:Int,dxUnconsumed:Int,dyUnconsumed:Int,offsetInWindow:Int[]):Boolean
// override fun dispatchPopulateAccessibilityEvent(event:AccessibilityEvent):Boolean
// override fun dispatchProvideStructure(structure:ViewStructure)
// override fun dispatchStartTemporaryDetach()
// override fun dispatchSystemUiVisibilityChanged(visibility:Int)
// override fun dispatchTouchEvent(event:MotionEvent):Boolean
// override fun dispatchTrackballEvent(event:MotionEvent):Boolean
// override fun dispatchUnhandledMove(focused:View,direction:Int):Boolean
// override fun dispatchWindowFocusChanged(hasFocus:Boolean)
// override fun dispatchWindowSystemUiVisiblityChanged(visible:Int)
// override fun dispatchWindowVisibilityChanged(visibility:Int)
// override fun draw(canvas:Canvas)
// override fun drawableHotspotChanged(x:Float,y:Float)
// override fun findFocus():View
// override fun findViewById(id:Int):View
// override fun findViewsWithText(outViews:ArrayList&lt;View&gt;,searched:CharSequence,flags:Int)
// override fun findViewWithTag(tag:Object):View
// override fun focusSearch(direction:Int):View
// override fun forceHasOverlappingRendering(hasOverlappingRendering:Boolean)
// override fun forceLayout()
// override fun generateViewId():Int
// override fun getAccessibilityClassName():CharSequence
// override fun getAccessibilityLiveRegion():Int
// override fun getAccessibilityNodeProvider():AccessibilityNodeProvider
// override fun getAccessibilityTraversalAfter():Int
// override fun getAccessibilityTraversalBefore():Int
// override fun getAlpha():Float
// override fun getAnimation():Animation
// override fun getApplicationWindowToken():IBinder
// override fun getBackground():Drawable
// override fun getBackgroundTintList():ColorStateList
// override fun getBackgroundTintMode():PorterDuff.Mode
// override fun getBaseline():Int
// override fun getBottom():Int
// override fun getCameraDistance():Float
// override fun getClipBounds():Rect
// override fun getClipBounds(outRect:Rect):Boolean
// override fun getClipToOutline():Boolean
// override fun getContentDescription():CharSequence
// override fun getContext():Context
// override fun getDefaultSize(size:Int,measureSpec:Int):Int
// override fun getDisplay():Display
// override fun getDrawableState():Int[]
// override fun getDrawingCache():Bitmap
// override fun getDrawingCache(autoScale:Boolean):Bitmap
// override fun getDrawingCacheBackgroundColor():Int
// override fun getDrawingCacheQuality():Int
// override fun getDrawingRect(outRect:Rect)
// override fun getDrawingTime():Long
// override fun getElevation():Float
// override fun getFilterTouchesWhenObscured():Boolean
// override fun getFitsSystemWindows():Boolean
// override fun getFocusables(direction:Int):ArrayList&lt;View&gt;
// override fun getFocusedRect(r:Rect)
// override fun getForeground():Drawable
// override fun getForegroundGravity():Int
// override fun getForegroundTintList():ColorStateList
// override fun getForegroundTintMode():PorterDuff.Mode
// override fun getGlobalVisibleRect(r:Rect):Boolean
// override fun getGlobalVisibleRect(r:Rect,globalOffset:Point):Boolean
// override fun getHandler():Handler
// override fun getHasOverlappingRendering():Boolean
// override fun getHeight():Int
// override fun getHitRect(outRect:Rect)
// override fun getHorizontalFadingEdgeLength():Int
// override fun getId():Int
// override fun getImportantForAccessibility():Int
// override fun getKeepScreenOn():Boolean
// override fun getKeyDispatcherState():KeyEvent.DispatcherState
// override fun getLabelFor():Int
// override fun getLayerType():Int
// override fun getLayoutDirection():Int
// override fun getLayoutParams():ViewGroup.LayoutParams
// override fun getLeft():Int
// override fun getLocalVisibleRect(r:Rect):Boolean
// override fun getLocationInWindow(outLocation:Int[])
// override fun getLocationOnScreen(outLocation:Int[])
// override fun getMatrix():Matrix
// override fun getMeasuredHeight():Int
// override fun getMeasuredHeightAndState():Int
// override fun getMeasuredState():Int
// override fun getMeasuredWidth():Int
// override fun getMeasuredWidthAndState():Int
// override fun getMinimumHeight():Int
// override fun getMinimumWidth():Int
// override fun getNextFocusDownId():Int
// override fun getNextFocusForwardId():Int
// override fun getNextFocusLeftId():Int
// override fun getNextFocusRightId():Int
// override fun getNextFocusUpId():Int
// override fun getOnFocusChangeListener():View.OnFocusChangeListener
// override fun getOutlineProvider():ViewOutlineProvider
// override fun getOverlay():ViewOverlay
// override fun getOverScrollMode():Int
// override fun getPaddingBottom():Int
// override fun getPaddingEnd():Int
// override fun getPaddingLeft():Int
// override fun getPaddingRight():Int
// override fun getPaddingStart():Int
// override fun getPaddingTop():Int
// override fun getParent():ViewParent
// override fun getParentForAccessibility():ViewParent
// override fun getPivotX():Float
// override fun getPivotY():Float
// override fun getPointerIcon():PointerIcon
// override fun getResources():Resources
// override fun getRight():Int
// override fun getRootView():View
// override fun getRootWindowInsets():WindowInsets
// override fun getRotation():Float
// override fun getRotationX():Float
// override fun getRotationY():Float
// override fun getScaleX():Float
// override fun getScaleY():Float
// override fun getScrollBarDefaultDelayBeforeFade():Int
// override fun getScrollBarFadeDuration():Int
// override fun getScrollBarSize():Int
// override fun getScrollBarStyle():Int
// override fun getScrollIndicators():Int
// override fun getScrollX():Int
// override fun getScrollY():Int
// override fun getSolidColor():Int
// override fun getStateListAnimator():StateListAnimator
// override fun getSystemUiVisibility():Int
// override fun getTag():Object
// override fun getTag(key:Int):Object
// override fun getTextAlignment():Int
// override fun getTextDirection():Int
// override fun getTop():Int
// override fun getTouchables():ArrayList&lt;View&gt;
// override fun getTouchDelegate():TouchDelegate
// override fun getTransitionName():String
// override fun getTranslationX():Float
// override fun getTranslationY():Float
// override fun getTranslationZ():Float
// override fun getVerticalFadingEdgeLength():Int
// override fun getVerticalScrollbarPosition():Int
// override fun getVerticalScrollbarWidth():Int
// override fun getViewTreeObserver():ViewTreeObserver
// override fun getVisibility():Int
// override fun getWidth():Int
// override fun getWindowId():WindowId
// override fun getWindowSystemUiVisibility():Int
// override fun getWindowToken():IBinder
// override fun getWindowVisibility():Int
// override fun getWindowVisibleDisplayFrame(outRect:Rect)
// override fun getX():Float
// override fun getY():Float
// override fun getZ():Float
// override fun hasFocus():Boolean
// override fun hasFocusable():Boolean
// override fun hasNestedScrollingParent():Boolean
// override fun hasOnClickListeners():Boolean
// override fun hasOverlappingRendering():Boolean
// override fun hasTransientState():Boolean
// override fun hasWindowFocus():Boolean
// override fun inflate(context:Context,resource:Int,root:ViewGroup):View
// override fun invalidate()
// override fun invalidate(dirty:Rect)
// override fun invalidate(l:Int,t:Int,r:Int,b:Int)
// override fun invalidateDrawable(drawable:Drawable)
// override fun invalidateOutline()
// override fun isAccessibilityFocused():Boolean
// override fun isActivated():Boolean
// override fun isAttachedToWindow():Boolean
// override fun isClickable():Boolean
// override fun isContextClickable():Boolean
// override fun isDirty():Boolean
// override fun isDrawingCacheEnabled():Boolean
// override fun isDuplicateParentStateEnabled():Boolean
// override fun isEnabled():Boolean
// override fun isFocusable():Boolean
// override fun isFocusableInTouchMode():Boolean
// override fun isFocused():Boolean
// override fun isHapticFeedbackEnabled():Boolean
// override fun isHardwareAccelerated():Boolean
// override fun isHorizontalFadingEdgeEnabled():Boolean
// override fun isHorizontalScrollBarEnabled():Boolean
// override fun isHovered():Boolean
// override fun isImportantForAccessibility():Boolean
// override fun isInEditMode():Boolean
// override fun isInLayout():Boolean
// override fun isInTouchMode():Boolean
// override fun isLaidOut():Boolean
// override fun isLayoutDirectionResolved():Boolean
// override fun isLayoutRequested():Boolean
// override fun isLongClickable():Boolean
// override fun isNestedScrollingEnabled():Boolean
// override fun isOpaque():Boolean
// override fun isPaddingRelative():Boolean
// override fun isPressed():Boolean
// override fun isSaveEnabled():Boolean
// override fun isSaveFromParentEnabled():Boolean
// override fun isScrollbarFadingEnabled():Boolean
// override fun isScrollContainer():Boolean
// override fun isSelected():Boolean
// override fun isShown():Boolean
// override fun isSoundEffectsEnabled():Boolean
// override fun isTemporarilyDetached():Boolean
// override fun isTextAlignmentResolved():Boolean
// override fun isTextDirectionResolved():Boolean
// override fun isVerticalFadingEdgeEnabled():Boolean
// override fun isVerticalScrollBarEnabled():Boolean
// override fun jumpDrawablesToCurrentState()
// override fun layout(l:Int,t:Int,r:Int,b:Int)
// override fun measure(widthMeasureSpec:Int,heightMeasureSpec:Int)
// override fun offsetLeftAndRight(offset:Int)
// override fun offsetTopAndBottom(offset:Int)
// override fun onApplyWindowInsets(insets:WindowInsets):WindowInsets
// override fun onCancelPendingInputEvents()
// override fun onCheckIsTextEditor():Boolean
// override fun onCreateInputConnection(outAttrs:EditorInfo):InputConnection
// override fun onDragEvent(event:DragEvent):Boolean
// override fun onDrawForeground(canvas:Canvas)
// override fun onFilterTouchEventForSecurity(event:MotionEvent):Boolean
// override fun onFinishTemporaryDetach()
// override fun onGenericMotionEvent(event:MotionEvent):Boolean
// override fun onHoverChanged(hovered:Boolean)
// override fun onHoverEvent(event:MotionEvent):Boolean
// override fun onInitializeAccessibilityEvent(event:AccessibilityEvent)
// override fun onInitializeAccessibilityNodeInfo(info:AccessibilityNodeInfo)
// override fun onKeyDown(keyCode:Int,event:KeyEvent):Boolean
// override fun onKeyLongPress(keyCode:Int,event:KeyEvent):Boolean
// override fun onKeyMultiple(keyCode:Int,repeatCount:Int,event:KeyEvent):Boolean
// override fun onKeyPreIme(keyCode:Int,event:KeyEvent):Boolean
// override fun onKeyShortcut(keyCode:Int,event:KeyEvent):Boolean
// override fun onKeyUp(keyCode:Int,event:KeyEvent):Boolean
// override fun onPopulateAccessibilityEvent(event:AccessibilityEvent)
// override fun onProvideStructure(structure:ViewStructure)
// override fun onProvideVirtualStructure(structure:ViewStructure)
// override fun onResolvePointerIcon(event:MotionEvent,pointerIndex:Int):PointerIcon
// override fun onRtlPropertiesChanged(layoutDirection:Int)
// override fun onScreenStateChanged(screenState:Int)
// override fun onStartTemporaryDetach()
// override fun onTouchEvent(event:MotionEvent):Boolean
// override fun onTrackballEvent(event:MotionEvent):Boolean
// override fun onVisibilityAggregated(isVisible:Boolean)
// override fun onWindowFocusChanged(hasWindowFocus:Boolean)
// override fun onWindowSystemUiVisibilityChanged(visible:Int)
// override fun performAccessibilityAction(action:Int,arguments:Bundle):Boolean
// override fun performClick():Boolean
// override fun performContextClick():Boolean
// override fun performContextClick(x:Float,y:Float):Boolean
// override fun performHapticFeedback(feedbackConstant:Int):Boolean
// override fun performHapticFeedback(feedbackConstant:Int,flags:Int):Boolean
// override fun performLongClick():Boolean
// override fun performLongClick(x:Float,y:Float):Boolean
// override fun playSoundEffect(soundConstant:Int)
// override fun post(action:Runnable):Boolean
// override fun postDelayed(action:Runnable,delayMillis:Long):Boolean
// override fun postInvalidate()
// override fun postInvalidate(left:Int,top:Int,right:Int,bottom:Int)
// override fun postInvalidateDelayed(delayMilliseconds:Long)
// override fun postInvalidateDelayed(delayMilliseconds:Long,left:Int,top:Int,right:Int,bottom:Int)
// override fun postInvalidateOnAnimation()
// override fun postInvalidateOnAnimation(left:Int,top:Int,right:Int,bottom:Int)
// override fun postOnAnimation(action:Runnable)
// override fun postOnAnimationDelayed(action:Runnable,delayMillis:Long)
// override fun refreshDrawableState()
// override fun removeCallbacks(action:Runnable):Boolean
// override fun removeOnAttachStateChangeListener(listener:View.OnAttachStateChangeListener)
// override fun removeOnLayoutChangeListener(listener:View.OnLayoutChangeListener)
// override fun requestApplyInsets()
// override fun requestFitSystemWindows()
// override fun requestFocus():Boolean
// override fun requestFocus(direction:Int):Boolean
// override fun requestFocus(direction:Int,previouslyFocusedRect:Rect):Boolean
// override fun requestFocusFromTouch():Boolean
// override fun requestLayout()
// override fun requestRectangleOnScreen(rectangle:Rect):Boolean
// override fun requestRectangleOnScreen(rectangle:Rect,immediate:Boolean):Boolean
// override fun requestUnbufferedDispatch(event:MotionEvent)
// override fun resolveSize(size:Int,measureSpec:Int):Int
// override fun resolveSizeAndState(size:Int,measureSpec:Int,childMeasuredState:Int):Int
// override fun restoreHierarchyState(container:SparseArray&lt;Parcelable&gt;)
// override fun saveHierarchyState(container:SparseArray&lt;Parcelable&gt;)
// override fun scheduleDrawable(who:Drawable,what:Runnable,when:Long)
// override fun scrollBy(x:Int,y:Int)
// override fun scrollTo(x:Int,y:Int)
// override fun sendAccessibilityEvent(eventType:Int)
// override fun sendAccessibilityEventUnchecked(event:AccessibilityEvent)
// override fun setAccessibilityDelegate(delegate:View.AccessibilityDelegate)
// override fun setAccessibilityLiveRegion(mode:Int)
// override fun setAccessibilityTraversalAfter(afterId:Int)
// override fun setAccessibilityTraversalBefore(beforeId:Int)
// override fun setActivated(activated:Boolean)
// override fun setAlpha(alpha:Float)
// override fun setAnimation(animation:Animation)
// override fun setBackground(background:Drawable)
// override fun setBackgroundColor(color:Int)
// override fun setBackgroundDrawable(background:Drawable)
// override fun setBackgroundResource(resid:Int)
// override fun setBackgroundTintList(tint:ColorStateList)
// override fun setBackgroundTintMode(tintMode:PorterDuff.Mode)
// override fun setBottom(bottom:Int)
// override fun setCameraDistance(distance:Float)
// override fun setClickable(clickable:Boolean)
// override fun setClipBounds(clipBounds:Rect)
// override fun setClipToOutline(clipToOutline:Boolean)
// override fun setContentDescription(contentDescription:CharSequence)
// override fun setContextClickable(contextClickable:Boolean)
// override fun setDrawingCacheBackgroundColor(color:Int)
// override fun setDrawingCacheEnabled(enabled:Boolean)
// override fun setDrawingCacheQuality(quality:Int)
// override fun setDuplicateParentStateEnabled(enabled:Boolean)
// override fun setElevation(elevation:Float)
// override fun setEnabled(enabled:Boolean)
// override fun setFadingEdgeLength(length:Int)
// override fun setFilterTouchesWhenObscured(enabled:Boolean)
// override fun setFitsSystemWindows(fitSystemWindows:Boolean)
// override fun setFocusable(focusable:Boolean)
// override fun setFocusableInTouchMode(focusableInTouchMode:Boolean)
// override fun setForeground(foreground:Drawable)
// override fun setForegroundGravity(gravity:Int)
// override fun setForegroundTintList(tint:ColorStateList)
// override fun setForegroundTintMode(tintMode:PorterDuff.Mode)
// override fun setHapticFeedbackEnabled(hapticFeedbackEnabled:Boolean)
// override fun setHasTransientState(hasTransientState:Boolean)
// override fun setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled:Boolean)
// override fun setHorizontalScrollBarEnabled(horizontalScrollBarEnabled:Boolean)
// override fun setHovered(hovered:Boolean)
// override fun setId(id:Int)
// override fun setImportantForAccessibility(mode:Int)
// override fun setKeepScreenOn(keepScreenOn:Boolean)
// override fun setLabelFor(id:Int)
// override fun setLayerPaint(paint:Paint)
// override fun setLayerType(layerType:Int,paint:Paint)
// override fun setLayoutDirection(layoutDirection:Int)
// override fun setLayoutParams(params:ViewGroup.LayoutParams)
// override fun setLeft(left:Int)
// override fun setLongClickable(longClickable:Boolean)
// override fun setMinimumHeight(minHeight:Int)
// override fun setMinimumWidth(minWidth:Int)
// override fun setNestedScrollingEnabled(enabled:Boolean)
// override fun setNextFocusDownId(nextFocusDownId:Int)
// override fun setNextFocusForwardId(nextFocusForwardId:Int)
// override fun setNextFocusLeftId(nextFocusLeftId:Int)
// override fun setNextFocusRightId(nextFocusRightId:Int)
// override fun setNextFocusUpId(nextFocusUpId:Int)
// override fun setOnApplyWindowInsetsListener(listener:View.OnApplyWindowInsetsListener)
// override fun setOnClickListener(l:View.OnClickListener)
// override fun setOnContextClickListener(l:View.OnContextClickListener)
// override fun setOnCreateContextMenuListener(l:View.OnCreateContextMenuListener)
// override fun setOnDragListener(l:View.OnDragListener)
// override fun setOnFocusChangeListener(l:View.OnFocusChangeListener)
// override fun setOnGenericMotionListener(l:View.OnGenericMotionListener)
// override fun setOnHoverListener(l:View.OnHoverListener)
// override fun setOnKeyListener(l:View.OnKeyListener)
// override fun setOnLongClickListener(l:View.OnLongClickListener)
// override fun setOnScrollChangeListener(l:View.OnScrollChangeListener)
// override fun setOnSystemUiVisibilityChangeListener(l:View.OnSystemUiVisibilityChangeListener)
// override fun setOnTouchListener(l:View.OnTouchListener)
// override fun setOutlineProvider(provider:ViewOutlineProvider)
// override fun setOverScrollMode(overScrollMode:Int)
// override fun setPadding(left:Int,top:Int,right:Int,bottom:Int)
// override fun setPaddingRelative(start:Int,top:Int,end:Int,bottom:Int)
// override fun setPivotX(pivotX:Float)
// override fun setPivotY(pivotY:Float)
// override fun setPointerIcon(pointerIcon:PointerIcon)
// override fun setPressed(pressed:Boolean)
// override fun setRight(right:Int)
// override fun setRotation(rotation:Float)
// override fun setRotationX(rotationX:Float)
// override fun setRotationY(rotationY:Float)
// override fun setSaveEnabled(enabled:Boolean)
// override fun setSaveFromParentEnabled(enabled:Boolean)
// override fun setScaleX(scaleX:Float)
// override fun setScaleY(scaleY:Float)
// override fun setScrollBarDefaultDelayBeforeFade(scrollBarDefaultDelayBeforeFade:Int)
// override fun setScrollBarFadeDuration(scrollBarFadeDuration:Int)
// override fun setScrollbarFadingEnabled(fadeScrollbars:Boolean)
// override fun setScrollBarSize(scrollBarSize:Int)
// override fun setScrollBarStyle(style:Int)
// override fun setScrollContainer(isScrollContainer:Boolean)
// override fun setScrollIndicators(indicators:Int)
// override fun setScrollIndicators(indicators:Int,mask:Int)
// override fun setScrollX(value:Int)
// override fun setScrollY(value:Int)
// override fun setSelected(selected:Boolean)
// override fun setSoundEffectsEnabled(soundEffectsEnabled:Boolean)
// override fun setStateListAnimator(stateListAnimator:StateListAnimator)
// override fun setSystemUiVisibility(visibility:Int)
// override fun setTag(key:Int,tag:Object)
// override fun setTag(tag:Object)
// override fun setTextAlignment(textAlignment:Int)
// override fun setTextDirection(textDirection:Int)
// override fun setTop(top:Int)
// override fun setTouchDelegate(delegate:TouchDelegate)
// override fun setTransitionName(transitionName:String)
// override fun setTranslationX(translationX:Float)
// override fun setTranslationY(translationY:Float)
// override fun setTranslationZ(translationZ:Float)
// override fun setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled:Boolean)
// override fun setVerticalScrollBarEnabled(verticalScrollBarEnabled:Boolean)
// override fun setVerticalScrollbarPosition(position:Int)
// override fun setVisibility(visibility:Int)
// override fun setWillNotCacheDrawing(willNotCacheDrawing:Boolean)
// override fun setWillNotDraw(willNotDraw:Boolean)
// override fun setX(x:Float)
// override fun setY(y:Float)
// override fun setZ(z:Float)
// override fun showContextMenu():Boolean
// override fun showContextMenu(x:Float,y:Float):Boolean
// override fun startActionMode(callback:ActionMode.Callback):ActionMode
// override fun startActionMode(callback:ActionMode.Callback,type:Int):ActionMode
// override fun startAnimation(animation:Animation)
// override fun startDrag(data:ClipData,shadowBuilder:View.DragShadowBuilder,myLocalState:Object,flags:Int):Boolean
// override fun startDragAndDrop(data:ClipData,shadowBuilder:View.DragShadowBuilder,myLocalState:Object,flags:Int):Boolean
// override fun startNestedScroll(axes:Int):Boolean
// override fun stopNestedScroll()
// override fun toString():String
// override fun unscheduleDrawable(who:Drawable)
// override fun unscheduleDrawable(who:Drawable,what:Runnable)
// override fun updateDragShadow(shadowBuilder:View.DragShadowBuilder)
// override fun willNotCacheDrawing():Boolean
// override fun willNotDraw():Boolean


    override
    fun addChildrenForAccessibility(outChildren: ArrayList<View>) {
// Adds the children of this View relevant for accessibility to the given list  as output.
        TODO("not implemented")
// super.addChildrenForAccessibility(outChildren)
    }

    override fun addFocusables(views: ArrayList<View>, direction: Int) {
// Add any focusable views that are descendants of this view (possibly  including this view if it is focusable itself) to views.
        TODO("not implemented")
// super.addFocusables(views,direction)
    }

    override fun addFocusables(views: ArrayList<View>, direction: Int, focusableMode: Int) {
// Adds any focusable views that are descendants of this view (possibly  including this view if it is focusable itself) to views.
        TODO("not implemented")
// super.addFocusables(views,direction,focusableMode)
    }

    override fun addOnAttachStateChangeListener(listener: View.OnAttachStateChangeListener) {
// Add a listener for attach state changes.
        TODO("not implemented")
// super.addOnAttachStateChangeListener(listener)
    }

    override fun addOnLayoutChangeListener(listener: View.OnLayoutChangeListener) {
// Add a listener that will be called when the bounds of the view change due to  layout processing.
        TODO("not implemented")
// super.addOnLayoutChangeListener(listener)
    }

    override fun addTouchables(views: ArrayList<View>) {
// Add any touchable views that are descendants of this view (possibly  including this view if it is touchable itself) to views.
        TODO("not implemented")
// super.addTouchables(views)
    }

    override fun animate(): ViewPropertyAnimator {
// This method returns a ViewPropertyAnimator object, which can be used to animate  specific properties on this View.
        TODO("not implemented")
// return super.animate()
    }

    override fun announceForAccessibility(text: CharSequence) {
// Convenience method for sending a TYPE_ANNOUNCEMENT  AccessibilityEvent to make an announcement which is related to some  sort of a context change for which none of the events representing UI transitions  is a good fit.
        TODO("not implemented")
// super.announceForAccessibility(text)
    }

    override fun bringToFront() {
// Change the view's z order in the tree, so it's on top of other sibling  views.
        TODO("not implemented")
// super.bringToFront()
    }

    override fun buildDrawingCache(autoScale: Boolean) {
//
        TODO("not implemented")
// super.buildDrawingCache(autoScale)
    }

    override fun buildDrawingCache() {
//
        TODO("not implemented")
// super.buildDrawingCache()
    }

    override fun buildLayer() {
// Forces this view's layer to be created and this view to be rendered  into its layer.
        TODO("not implemented")
// super.buildLayer()
    }

    override fun callOnClick(): Boolean {
// Directly call any attached OnClickListener.
        TODO("not implemented")
// return super.callOnClick()
    }

    override fun canResolveLayoutDirection(): Boolean {
// Check if layout direction resolution can be done.
        TODO("not implemented")
// return super.canResolveLayoutDirection()
    }

    override fun canResolveTextAlignment(): Boolean {
// Check if text alignment resolution can be done.
        TODO("not implemented")
// return super.canResolveTextAlignment()
    }

    override fun canResolveTextDirection(): Boolean {
// Check if text direction resolution can be done.
        TODO("not implemented")
// return super.canResolveTextDirection()
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
// Check if this view can be scrolled horizontally in a certain direction.
        TODO("not implemented")
// return super.canScrollHorizontally(direction)
    }

    override fun canScrollVertically(direction: Int): Boolean {
// Check if this view can be scrolled vertically in a certain direction.
        TODO("not implemented")
// return super.canScrollVertically(direction)
    }


    override fun cancelLongPress() {
// Cancels a pending long press.
        TODO("not implemented")
// super.cancelLongPress()
    }


    override fun checkInputConnectionProxy(view: View): Boolean {
// Called by the InputMethodManager  when a view who is not the current  input connection target is trying to make a call on the manager.
        TODO("not implemented")
// return super.checkInputConnectionProxy(view)
    }

    override fun clearAnimation() {
// Cancels any animations for this view.
        TODO("not implemented")
// super.clearAnimation()
    }

    override fun clearFocus() {
// Called when this view wants to give up focus.
        TODO("not implemented")
// super.clearFocus()
    }


    override fun computeScroll() {
// Called by a parent to request that a child update its values for mScrollX  and mScrollY if necessary.
        TODO("not implemented")
// super.computeScroll()
    }

    override fun computeSystemWindowInsets(inx: WindowInsets, outLocalInsets: Rect): WindowInsets {
// Compute insets that should be consumed by this view and the ones that should propagate  to those under it.
        TODO("not implemented")
// return super.computeSystemWindowInsets(in,outLocalInsets)
    }

    override fun createAccessibilityNodeInfo(): AccessibilityNodeInfo {
// Returns an AccessibilityNodeInfo representing this view from the  point of view of an AccessibilityService.
        TODO("not implemented")
// return super.createAccessibilityNodeInfo()
    }

    override fun createContextMenu(menu: ContextMenu) {
// Show the context menu for this view.
        TODO("not implemented")
// super.createContextMenu(menu)
    }

    override fun destroyDrawingCache() {
//
        TODO("not implemented")
// super.destroyDrawingCache()
    }

    override fun dispatchApplyWindowInsets(insets: WindowInsets): WindowInsets {
// Request to apply the given window insets to this view or another view in its subtree.
        TODO("not implemented")
// return super.dispatchApplyWindowInsets(insets)
    }

    override fun dispatchConfigurationChanged(newConfig: Configuration) {
// Dispatch a notification about a resource configuration change down  the view hierarchy.
        TODO("not implemented")
// super.dispatchConfigurationChanged(newConfig)
    }

    override fun dispatchDisplayHint(hint: Int) {
// Dispatch a hint about whether this view is displayed.
        TODO("not implemented")
// super.dispatchDisplayHint(hint)
    }

    override fun dispatchDragEvent(event: DragEvent): Boolean {
// Detects if this View is enabled and has a drag event listener.
        TODO("not implemented")
// return super.dispatchDragEvent(event)
    }

    override fun dispatchDrawableHotspotChanged(x: Float, y: Float) {
// Dispatches drawableHotspotChanged to all of this View's children.
        TODO("not implemented")
// super.dispatchDrawableHotspotChanged(x,y)
    }

    override fun dispatchFinishTemporaryDetach() {
// Dispatch onFinishTemporaryDetach() to this View and its direct children if this is  a container View.
        TODO("not implemented")
        super.dispatchFinishTemporaryDetach()
    }

    override fun dispatchGenericMotionEvent(event: MotionEvent): Boolean {
// Dispatch a generic motion event.
        TODO("not implemented")
// return super.dispatchGenericMotionEvent(event)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
// Dispatch a key event to the next view on the focus path.
        TODO("not implemented")
// return super.dispatchKeyEvent(event)
    }

    override fun dispatchKeyEventPreIme(event: KeyEvent): Boolean {
// Dispatch a key event before it is processed by any input method  associated with the view hierarchy.
        TODO("not implemented")
// return super.dispatchKeyEventPreIme(event)
    }

    override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
// Dispatches a key shortcut event.
        TODO("not implemented")
// return super.dispatchKeyShortcutEvent(event)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
// Dispatch a fling to a nested scrolling parent.
        TODO("not implemented")
// return super.dispatchNestedFling(velocityX,velocityY,consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
// Dispatch a fling to a nested scrolling parent before it is processed by this view.
        TODO("not implemented")
// return super.dispatchNestedPreFling(velocityX,velocityY)
    }

    override fun dispatchNestedPrePerformAccessibilityAction(action: Int, arguments: Bundle): Boolean {
// Report an accessibility action to this view's parents for delegated processing.
        TODO("not implemented")
// return super.dispatchNestedPrePerformAccessibilityAction(action,arguments)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray, offsetInWindow: IntArray): Boolean {
// Dispatch one step of a nested scroll in progress before this view consumes any portion of it.
        TODO("not implemented")
// return super.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow)
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray
    ): Boolean {
// Dispatch one step of a nested scroll in progress.
        TODO("not implemented")
// return super.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow)
    }

    override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent): Boolean {
// Dispatches an AccessibilityEvent to the View first and then  to its children for adding their text content to the event.
        TODO("not implemented")
// return super.dispatchPopulateAccessibilityEvent(event)
    }

    override fun dispatchProvideStructure(structure: ViewStructure) {
// Dispatch creation of ViewStructure down the hierarchy.
        TODO("not implemented")
// super.dispatchProvideStructure(structure)
    }

    override fun dispatchStartTemporaryDetach() {
// Dispatch onStartTemporaryDetach() to this View and its direct children if this is  a container View.
        TODO("not implemented")
        super.dispatchStartTemporaryDetach()
    }

    override fun dispatchSystemUiVisibilityChanged(visibility: Int) {
// Dispatch callbacks to setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener) down  the view hierarchy.
        TODO("not implemented")
// super.dispatchSystemUiVisibilityChanged(visibility)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
// Pass the touch screen motion event down to the target view, or this  view if it is the target.
        TODO("not implemented")
// return super.dispatchTouchEvent(event)
    }

    override fun dispatchTrackballEvent(event: MotionEvent): Boolean {
// Pass a trackball motion event down to the focused view.
        TODO("not implemented")
// return super.dispatchTrackballEvent(event)
    }

    override fun dispatchUnhandledMove(focused: View, direction: Int): Boolean {
// This method is the last chance for the focused view and its ancestors to  respond to an arrow key.
        TODO("not implemented")
// return super.dispatchUnhandledMove(focused,direction)
    }

    override fun dispatchWindowFocusChanged(hasFocus: Boolean) {
// Called when the window containing this view gains or loses window focus.
        TODO("not implemented")
// super.dispatchWindowFocusChanged(hasFocus)
    }

    override fun dispatchWindowSystemUiVisiblityChanged(visible: Int) {
// Dispatch callbacks to onWindowSystemUiVisibilityChanged(int) down  the view hierarchy.
        TODO("not implemented")
// super.dispatchWindowSystemUiVisiblityChanged(visible)
    }

    override fun dispatchWindowVisibilityChanged(visibility: Int) {
// Dispatch a window visibility change down the view hierarchy.
        TODO("not implemented")
// super.dispatchWindowVisibilityChanged(visibility)
    }

    override fun draw(canvas: Canvas) {
// Manually render this view (and all of its children) to the given Canvas.

        super.draw(canvas)
    }

    override fun drawableHotspotChanged(x: Float, y: Float) {
// This function is called whenever the view hotspot changes and needs to  be propagated to drawables or child views managed by the view.
        TODO("not implemented")
        super.drawableHotspotChanged(x, y)
    }

    override fun findFocus(): View {
// Find the view in the hierarchy rooted at this view that currently has  focus.
        TODO("not implemented")
// return super.findFocus()
    }


    override fun findViewsWithText(outViews: ArrayList<View>, searched: CharSequence, flags: Int) {
// Finds the Views that contain given text.
        TODO("not implemented")
// super.findViewsWithText(outViews,searched,flags)
    }

    override fun focusSearch(direction: Int): View {
// Find the nearest view in the specified direction that can take focus.
        TODO("not implemented")
// return super.focusSearch(direction)
    }

    override fun forceHasOverlappingRendering(hasOverlappingRendering: Boolean) {
// Sets the behavior for overlapping rendering for this view (see hasOverlappingRendering() for more details on this behavior).
        TODO("not implemented")
// super.forceHasOverlappingRendering(hasOverlappingRendering)
    }

    override fun forceLayout() {
// Forces this view to be laid out during the next layout pass.
        TODO("not implemented")
// super.forceLayout()
    }



    override fun getAccessibilityClassName(): CharSequence {
// Return the class name of this object to be used for accessibility purposes.
        TODO("not implemented")
// return super.getAccessibilityClassName()
    }

    override fun getAccessibilityLiveRegion(): Int {
// Gets the live region mode for this View.
        TODO("not implemented")
// return super.getAccessibilityLiveRegion()
    }

    override fun getAccessibilityNodeProvider(): AccessibilityNodeProvider {
// Gets the provider for managing a virtual view hierarchy rooted at this View  and reported to AccessibilityServices  that explore the window content.
        TODO("not implemented")
// return super.getAccessibilityNodeProvider()
    }

    override fun getAccessibilityTraversalAfter(): Int {
// Gets the id of a view after which this one is visited in accessibility traversal.
        TODO("not implemented")
// return super.getAccessibilityTraversalAfter()
    }

    override fun getAccessibilityTraversalBefore(): Int {
// Gets the id of a view before which this one is visited in accessibility traversal.
        TODO("not implemented")
// return super.getAccessibilityTraversalBefore()
    }

    override fun getAlpha(): Float {
// The opacity of the view.
        TODO("not implemented")
// return super.getAlpha()
    }

    override fun getAnimation(): Animation {
// Get the animation currently associated with this view.
        TODO("not implemented")
// return super.getAnimation()
    }

    override fun getApplicationWindowToken(): IBinder {
// Retrieve a unique token identifying the top-level "real" window of  the window that this view is attached to.
        TODO("not implemented")
// return super.getApplicationWindowToken()
    }

    override fun getBackground(): Drawable {
// Gets the background drawable
        TODO("not implemented")
// return super.getBackground()
    }

    override fun getBackgroundTintList(): ColorStateList {
// Return the tint applied to the background drawable, if specified.
        TODO("not implemented")
// return super.getBackgroundTintList()
    }

    override fun getBackgroundTintMode(): PorterDuff.Mode {
// Return the blending mode used to apply the tint to the background  drawable, if specified.
        TODO("not implemented")
// return super.getBackgroundTintMode()
    }

    override fun getBaseline(): Int {
//
        TODO("not implemented")
// return super.getBaseline()
    }


    override fun getCameraDistance(): Float {
// Gets the distance along the Z axis from the camera to this view.
        TODO("not implemented")
// return super.getCameraDistance()
    }

    override fun getClipBounds(outRect: Rect): Boolean {
// Populates an output rectangle with the clip bounds of the view,  returning true if successful or false if the view's  clip bounds are null.
        TODO("not implemented")
// return super.getClipBounds(outRect)
    }

    override fun getClipBounds(): Rect {
// Returns a copy of the current clipBounds.
        TODO("not implemented")
// return super.getClipBounds()
    }


    override fun getDisplay(): Display {
// Gets the logical display to which the view's window has been attached.
        TODO("not implemented")
// return super.getDisplay()
    }


    override fun getDrawingCache(): Bitmap {
//
        TODO("not implemented")
// return super.getDrawingCache()
    }

    override fun getDrawingCache(autoScale: Boolean): Bitmap {
//
        TODO("not implemented")
// return super.getDrawingCache(autoScale)
    }

    override fun getDrawingCacheBackgroundColor(): Int {
//
        TODO("not implemented")
// return super.getDrawingCacheBackgroundColor()
    }

    override fun getDrawingCacheQuality(): Int {
// Returns the quality of the drawing cache.
        TODO("not implemented")
// return super.getDrawingCacheQuality()
    }

    override fun getDrawingRect(outRect: Rect) {
// Return the visible drawing bounds of your view.
        TODO("not implemented")
// super.getDrawingRect(outRect)
    }

    override fun getDrawingTime(): Long {
//
        TODO("not implemented")
// return super.getDrawingTime()
    }

    override fun getElevation(): Float {
// The base elevation of this view relative to its parent, in pixels.
        TODO("not implemented")
// return super.getElevation()
    }

    override fun getFilterTouchesWhenObscured(): Boolean {
// Gets whether the framework should discard touches when the view's  window is obscured by another visible window.
        TODO("not implemented")
// return super.getFilterTouchesWhenObscured()
    }

    override fun getFitsSystemWindows(): Boolean {
// Check for state of setFitsSystemWindows(boolean).
        TODO("not implemented")
// return super.getFitsSystemWindows()
    }


    override fun getFocusedRect(r: Rect) {
// When a view has focus and the user navigates away from it, the next view is searched for  starting from the rectangle filled in by this method.
        TODO("not implemented")
// super.getFocusedRect(r)
    }

    override fun getForeground(): Drawable {
// Returns the drawable used as the foreground of this View.
        TODO("not implemented")
// return super.getForeground()
    }

    override fun getForegroundGravity(): Int {
// Describes how the foreground is positioned.
        TODO("not implemented")
// return super.getForegroundGravity()
    }

    override fun getForegroundTintList(): ColorStateList {
// Return the tint applied to the foreground drawable, if specified.
        TODO("not implemented")
// return super.getForegroundTintList()
    }

    override fun getForegroundTintMode(): PorterDuff.Mode {
// Return the blending mode used to apply the tint to the foreground  drawable, if specified.
        TODO("not implemented")
// return super.getForegroundTintMode()
    }


    override fun getGlobalVisibleRect(r: Rect, globalOffset: Point): Boolean {
// If some part of this view is not clipped by any of its parents, then  return that area in r in global (root) coordinates.
        TODO("not implemented")
// return super.getGlobalVisibleRect(r,globalOffset)
    }

    override fun getHandler(): Handler {
//
        TODO("not implemented")
// return super.getHandler()
    }


    override fun getHitRect(outRect: Rect) {
// Hit rectangle in parent's coordinates
        TODO("not implemented")
// super.getHitRect(outRect)
    }

    override fun getHorizontalFadingEdgeLength(): Int {
// Returns the size of the horizontal faded edges used to indicate that more  content in this view is visible.
        TODO("not implemented")
// return super.getHorizontalFadingEdgeLength()
    }

    override fun getId(): Int {
// Returns this view's identifier.
        TODO("not implemented")
// return super.getId()
    }

    override fun getImportantForAccessibility(): Int {
// Gets the mode for determining whether this View is important for accessibility  which is if it fires accessibility events and if it is reported to  accessibility services that query the screen.
        TODO("not implemented")
// return super.getImportantForAccessibility()
    }

    override fun getKeepScreenOn(): Boolean {
// Returns whether the screen should remain on, corresponding to the current  value of KEEP_SCREEN_ON.
        TODO("not implemented")
// return super.getKeepScreenOn()
    }

    override fun getKeyDispatcherState(): KeyEvent.DispatcherState {
// Return the global KeyEvent.DispatcherState  for this view's window.
        TODO("not implemented")
// return super.getKeyDispatcherState()
    }

    override fun getLabelFor(): Int {
// Gets the id of a view for which this view serves as a label for  accessibility purposes.
        TODO("not implemented")
// return super.getLabelFor()
    }

    override fun getLayerType(): Int {
// Indicates what type of layer is currently associated with this view.
        TODO("not implemented")
// return super.getLayerType()
    }

    override fun getLayoutDirection(): Int {
// Returns the resolved layout direction for this view.
        TODO("not implemented")
// return super.getLayoutDirection()
    }

    override fun getLayoutParams(): ViewGroup.LayoutParams {
// Get the LayoutParams associated with this view.
        TODO("not implemented")
// return super.getLayoutParams()
    }


    override fun getLocationInWindow(outLocation: IntArray) {
//
        TODO("not implemented")
// super.getLocationInWindow(outLocation)
    }

    override fun getLocationOnScreen(outLocation: IntArray) {
//
        TODO("not implemented")
// super.getLocationOnScreen(outLocation)
    }

    override fun getMatrix(): Matrix {
// The transform matrix of this view, which is calculated based on the current  rotation, scale, and pivot properties.
        TODO("not implemented")
// return super.getMatrix()
    }


    override fun getMinimumHeight(): Int {
// Returns the minimum height of the view.
        TODO("not implemented")
// return super.getMinimumHeight()
    }

    override fun getMinimumWidth(): Int {
// Returns the minimum width of the view.
        TODO("not implemented")
// return super.getMinimumWidth()
    }

    override fun getNextFocusDownId(): Int {
// Gets the id of the view to use when the next focus is FOCUS_DOWN.
        TODO("not implemented")
// return super.getNextFocusDownId()
    }

    override fun getNextFocusForwardId(): Int {
// Gets the id of the view to use when the next focus is FOCUS_FORWARD.
        TODO("not implemented")
// return super.getNextFocusForwardId()
    }

    override fun getNextFocusLeftId(): Int {
// Gets the id of the view to use when the next focus is FOCUS_LEFT.
        TODO("not implemented")
// return super.getNextFocusLeftId()
    }

    override fun getNextFocusRightId(): Int {
// Gets the id of the view to use when the next focus is FOCUS_RIGHT.
        TODO("not implemented")
// return super.getNextFocusRightId()
    }

    override fun getNextFocusUpId(): Int {
// Gets the id of the view to use when the next focus is FOCUS_UP.
        TODO("not implemented")
// return super.getNextFocusUpId()
    }

    override fun getOnFocusChangeListener(): View.OnFocusChangeListener {
// Returns the focus-change callback registered for this view.
        TODO("not implemented")
// return super.getOnFocusChangeListener()
    }

    override fun getOutlineProvider(): ViewOutlineProvider {
// Returns the current ViewOutlineProvider of the view, which generates the Outline  that defines the shape of the shadow it casts, and enables outline clipping.
        TODO("not implemented")
// return super.getOutlineProvider()
    }

    override fun getOverScrollMode(): Int {
// Returns the over-scroll mode for this view.
        TODO("not implemented")
// return super.getOverScrollMode()
    }

    override fun getOverlay(): ViewOverlay {
// Returns the overlay for this view, creating it if it does not yet exist.
        TODO("not implemented")
// return super.getOverlay()
    }

    override fun getPaddingBottom(): Int {
// Returns the bottom padding of this view.
        TODO("not implemented")
// return super.getPaddingBottom()
    }

    override fun getPaddingEnd(): Int {
// Returns the end padding of this view depending on its resolved layout direction.
        TODO("not implemented")
// return super.getPaddingEnd()
    }

    override fun getPaddingLeft(): Int {
// Returns the left padding of this view.
        TODO("not implemented")
// return super.getPaddingLeft()
    }

    override fun getPaddingRight(): Int {
// Returns the right padding of this view.
        TODO("not implemented")
// return super.getPaddingRight()
    }

    override fun getPaddingStart(): Int {
// Returns the start padding of this view depending on its resolved layout direction.
        TODO("not implemented")
// return super.getPaddingStart()
    }

    override fun getPaddingTop(): Int {
// Returns the top padding of this view.
        TODO("not implemented")
// return super.getPaddingTop()
    }


    override fun getParentForAccessibility(): ViewParent {
// Gets the parent for accessibility purposes.
        TODO("not implemented")
// return super.getParentForAccessibility()
    }

    override fun getPivotX(): Float {
// The x location of the point around which the view is rotated  and scaled.
        TODO("not implemented")
// return super.getPivotX()
    }

    override fun getPivotY(): Float {
// The y location of the point around which the view is rotated  and scaled.
        TODO("not implemented")
// return super.getPivotY()
    }

    override fun getPointerIcon(): PointerIcon {
// Gets the pointer icon for the current view.
        TODO("not implemented")
// return super.getPointerIcon()
    }

    override fun getResources(): Resources {
// Returns the resources associated with this view.
        TODO("not implemented")
// return super.getResources()
    }


    override fun getRootView(): View {
//
        TODO("not implemented")
// return super.getRootView()
    }

    override fun getRootWindowInsets(): WindowInsets {
// Provide original WindowInsets that are dispatched to the view hierarchy.
        TODO("not implemented")
// return super.getRootWindowInsets()
    }

    override fun getRotation(): Float {
// The degrees that the view is rotated around the pivot point.
        TODO("not implemented")
// return super.getRotation()
    }

    override fun getRotationX(): Float {
// The degrees that the view is rotated around the horizontal axis through the pivot point.
        TODO("not implemented")
// return super.getRotationX()
    }

    override fun getRotationY(): Float {
// The degrees that the view is rotated around the vertical axis through the pivot point.
        TODO("not implemented")
// return super.getRotationY()
    }

    override fun getScaleX(): Float {
// The amount that the view is scaled in x around the pivot point, as a proportion of  the view's unscaled width.
        TODO("not implemented")
// return super.getScaleX()
    }

    override fun getScaleY(): Float {
// The amount that the view is scaled in y around the pivot point, as a proportion of  the view's unscaled height.
        TODO("not implemented")
// return super.getScaleY()
    }

    override fun getScrollBarDefaultDelayBeforeFade(): Int {
// Returns the delay before scrollbars fade.
        TODO("not implemented")
// return super.getScrollBarDefaultDelayBeforeFade()
    }

    override fun getScrollBarFadeDuration(): Int {
// Returns the scrollbar fade duration.
        TODO("not implemented")
// return super.getScrollBarFadeDuration()
    }

    override fun getScrollBarSize(): Int {
// Returns the scrollbar size.
        TODO("not implemented")
// return super.getScrollBarSize()
    }

    override fun getScrollBarStyle(): Int {
//
        TODO("not implemented")
// return super.getScrollBarStyle()
    }

    override fun getScrollIndicators(): Int {
// Returns a bitmask representing the enabled scroll indicators.
        TODO("not implemented")
// return super.getScrollIndicators()
    }


    override fun getSolidColor(): Int {
// Override this if your view is known to always be drawn on top of a solid color background,  and needs to draw fading edges.
        TODO("not implemented")
// return super.getSolidColor()
    }

    override fun getStateListAnimator(): StateListAnimator {
// Returns the current StateListAnimator if exists.
        TODO("not implemented")
// return super.getStateListAnimator()
    }

    override fun getSystemUiVisibility(): Int {
// Returns the last setSystemUiVisibility(int) that this view has requested.
        TODO("not implemented")
// return super.getSystemUiVisibility()
    }

    override fun getTag(): Object {
// Returns this view's tag.
        TODO("not implemented")
// return super.getTag()
    }

    override fun getTag(key: Int): Object {
// Returns the tag associated with this view and the specified key.
        TODO("not implemented")
// return super.getTag(key)
    }

    override fun getTextAlignment(): Int {
// Return the resolved text alignment.
        TODO("not implemented")
// return super.getTextAlignment()
    }

    override fun getTextDirection(): Int {
// Return the resolved text direction.
        TODO("not implemented")
// return super.getTextDirection()
    }

    override fun getTouchDelegate(): TouchDelegate {
// Gets the TouchDelegate for this View.
        TODO("not implemented")
// return super.getTouchDelegate()
    }

    override fun getTouchables(): ArrayList<View> {
// Find and return all touchable views that are descendants of this view,  possibly including this view if it is touchable itself.
        TODO("not implemented")
// return super.getTouchables()
    }

    override fun getTransitionName(): String {
// Returns the name of the View to be used to identify Views in Transitions.
        TODO("not implemented")
// return super.getTransitionName()
    }

    override fun getTranslationX(): Float {
// The horizontal location of this view relative to its left position.
        TODO("not implemented")
// return super.getTranslationX()
    }

    override fun getTranslationY(): Float {
// The vertical location of this view relative to its top position.
        TODO("not implemented")
// return super.getTranslationY()
    }

    override fun getTranslationZ(): Float {
// The depth location of this view relative to its elevation.
        TODO("not implemented")
// return super.getTranslationZ()
    }

    override fun getVerticalFadingEdgeLength(): Int {
// Returns the size of the vertical faded edges used to indicate that more  content in this view is visible.
        TODO("not implemented")
// return super.getVerticalFadingEdgeLength()
    }

    override fun getVerticalScrollbarPosition(): Int {
//
        TODO("not implemented")
// return super.getVerticalScrollbarPosition()
    }

    override fun getVerticalScrollbarWidth(): Int {
// Returns the width of the vertical scrollbar.
        TODO("not implemented")
// return super.getVerticalScrollbarWidth()
    }

    override fun getViewTreeObserver(): ViewTreeObserver {
// Returns the ViewTreeObserver for this view's hierarchy.
        TODO("not implemented")
// return super.getViewTreeObserver()
    }

    override fun getVisibility(): Int {
// Returns the visibility status for this view.
        TODO("not implemented")
// return super.getVisibility()
    }



    override fun getWindowId(): WindowId {
// Retrieve the WindowId for the window this view is  currently attached to.
        TODO("not implemented")
// return super.getWindowId()
    }

    override fun getWindowSystemUiVisibility(): Int {
// Returns the current system UI visibility that is currently set for  the entire window.
        TODO("not implemented")
// return super.getWindowSystemUiVisibility()
    }

    override fun getWindowToken(): IBinder {
// Retrieve a unique token identifying the window this view is attached to.
        TODO("not implemented")
// return super.getWindowToken()
    }

    override fun getWindowVisibility(): Int {
// Returns the current visibility of the window this view is attached to  (either GONE, INVISIBLE, or VISIBLE).
        TODO("not implemented")
// return super.getWindowVisibility()
    }

    override fun getWindowVisibleDisplayFrame(outRect: Rect) {
// Retrieve the overall visible display size in which the window this view is  attached to has been positioned in.
        TODO("not implemented")
// super.getWindowVisibleDisplayFrame(outRect)
    }

    override fun getX(): Float {
// The visual x position of this view, in pixels.
        TODO("not implemented")
// return super.getX()
    }

    override fun getY(): Float {
// The visual y position of this view, in pixels.
        TODO("not implemented")
// return super.getY()
    }

    override fun getZ(): Float {
// The visual z position of this view, in pixels.
        TODO("not implemented")
// return super.getZ()
    }

    override fun hasFocus(): Boolean {
// Returns true if this view has focus itself, or is the ancestor of the  view that has focus.
        TODO("not implemented")
// return super.hasFocus()
    }

    override fun hasFocusable(): Boolean {
// Returns true if this view is focusable or if it contains a reachable View  for which hasFocusable() returns true.
        TODO("not implemented")
// return super.hasFocusable()
    }

    override fun hasNestedScrollingParent(): Boolean {
// Returns true if this view has a nested scrolling parent.
        TODO("not implemented")
// return super.hasNestedScrollingParent()
    }

    override fun hasOnClickListeners(): Boolean {
// Return whether this view has an attached OnClickListener.
        TODO("not implemented")
// return super.hasOnClickListeners()
    }

    override fun hasOverlappingRendering(): Boolean {
// Returns whether this View has content which overlaps.
        TODO("not implemented")
// return super.hasOverlappingRendering()
    }

    override fun hasTransientState(): Boolean {
// Indicates whether the view is currently tracking transient state that the  app should not need to concern itself with saving and restoring, but that  the framework should take special note to preserve when possible.
        TODO("not implemented")
// return super.hasTransientState()
    }

    override fun hasWindowFocus(): Boolean {
// Returns true if this view is in a window that currently has window focus.
        TODO("not implemented")
// return super.hasWindowFocus()
    }


    override fun invalidate() {
// Invalidate the whole view.
        TODO("not implemented")
// super.invalidate()
    }

    override fun invalidate(dirty: Rect) {
// Mark the area defined by dirty as needing to be drawn.
        TODO("not implemented")
// super.invalidate(dirty)
    }

    override fun invalidate(l: Int, t: Int, r: Int, b: Int) {
// Mark the area defined by the rect (l,t,r,b) as needing to be drawn.
        TODO("not implemented")
// super.invalidate(l,t,r,b)
    }

    override fun invalidateDrawable(drawable: Drawable) {
// Invalidates the specified Drawable.
        TODO("not implemented")
// super.invalidateDrawable(drawable)
    }

    override fun invalidateOutline() {
// Called to rebuild this View's Outline from its outline provider
        TODO("not implemented")
// super.invalidateOutline()
    }

    override fun isAccessibilityFocused(): Boolean {
// Returns whether this View is accessibility focused.
        TODO("not implemented")
// return super.isAccessibilityFocused()
    }

    override fun isActivated(): Boolean {
// Indicates the activation state of this view.
        TODO("not implemented")
// return super.isActivated()
    }

    override fun isAttachedToWindow(): Boolean {
// Returns true if this view is currently attached to a window.
        TODO("not implemented")
// return super.isAttachedToWindow()
    }

    override fun isClickable(): Boolean {
// Indicates whether this view reacts to click events or not.
        TODO("not implemented")
// return super.isClickable()
    }

    override fun isContextClickable(): Boolean {
// Indicates whether this view reacts to context clicks or not.
        TODO("not implemented")
// return super.isContextClickable()
    }

    override fun isDirty(): Boolean {
// True if this view has changed since the last time being drawn.
        TODO("not implemented")
// return super.isDirty()
    }

    override fun isDrawingCacheEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isDrawingCacheEnabled()
    }

    override fun isDuplicateParentStateEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isDuplicateParentStateEnabled()
    }

    override fun isEnabled(): Boolean {
// Returns the enabled status for this view.
        TODO("not implemented")
// return super.isEnabled()
    }


    override fun isFocused(): Boolean {
// Returns true if this view has focus
        TODO("not implemented")
// return super.isFocused()
    }

    override fun isHapticFeedbackEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isHapticFeedbackEnabled()
    }

    override fun isHardwareAccelerated(): Boolean {
//
        TODO("not implemented")
// return super.isHardwareAccelerated()
    }

    override fun isHorizontalFadingEdgeEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isHorizontalFadingEdgeEnabled()
    }

    override fun isHorizontalScrollBarEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isHorizontalScrollBarEnabled()
    }

    override fun isHovered(): Boolean {
// Returns true if the view is currently hovered.
        TODO("not implemented")
// return super.isHovered()
    }

    override fun isImportantForAccessibility(): Boolean {
// Computes whether this view should be exposed for accessibility.
        TODO("not implemented")
// return super.isImportantForAccessibility()
    }

    override fun isInEditMode(): Boolean {
// Indicates whether this View is currently in edit mode.
        TODO("not implemented")
// return super.isInEditMode()
    }

    override fun isInLayout(): Boolean {
// Returns whether the view hierarchy is currently undergoing a layout pass.
        TODO("not implemented")
// return super.isInLayout()
    }

    override fun isInTouchMode(): Boolean {
// Returns whether the device is currently in touch mode.
        TODO("not implemented")
// return super.isInTouchMode()
    }

    override fun isLaidOut(): Boolean {
// Returns true if this view has been through at least one layout since it  was last attached to or detached from a window.
        TODO("not implemented")
// return super.isLaidOut()
    }

    override fun isLayoutDirectionResolved(): Boolean {
//
        TODO("not implemented")
// return super.isLayoutDirectionResolved()
    }

    override fun isLayoutRequested(): Boolean {
//
        TODO("not implemented")
// return super.isLayoutRequested()
    }

    override fun isLongClickable(): Boolean {
// Indicates whether this view reacts to long click events or not.
        TODO("not implemented")
// return super.isLongClickable()
    }

    override fun isNestedScrollingEnabled(): Boolean {
// Returns true if nested scrolling is enabled for this view.
        TODO("not implemented")
// return super.isNestedScrollingEnabled()
    }

    override fun isOpaque(): Boolean {
// Indicates whether this View is opaque.
        TODO("not implemented")
// return super.isOpaque()
    }

    override fun isPaddingRelative(): Boolean {
// Return if the padding has been set through relative values  setPaddingRelative(int, int, int, int) or through
        TODO("not implemented")
// return super.isPaddingRelative()
    }

    override fun isPressed(): Boolean {
// Indicates whether the view is currently in pressed state.
        TODO("not implemented")
// return super.isPressed()
    }

    override fun isSaveEnabled(): Boolean {
// Indicates whether this view will save its state (that is,  whether its onSaveInstanceState() method will be called).
        TODO("not implemented")
// return super.isSaveEnabled()
    }

    override fun isSaveFromParentEnabled(): Boolean {
// Indicates whether the entire hierarchy under this view will save its  state when a state saving traversal occurs from its parent.
        TODO("not implemented")
// return super.isSaveFromParentEnabled()
    }

    override fun isScrollContainer(): Boolean {
// Indicates whether this view is one of the set of scrollable containers in  its window.
        TODO("not implemented")
// return super.isScrollContainer()
    }

    override fun isScrollbarFadingEnabled(): Boolean {
// Returns true if scrollbars will fade when this view is not scrolling
        TODO("not implemented")
// return super.isScrollbarFadingEnabled()
    }

    override fun isSelected(): Boolean {
// Indicates the selection state of this view.
        TODO("not implemented")
// return super.isSelected()
    }

    override fun isShown(): Boolean {
// Returns the visibility of this view and all of its ancestors
        TODO("not implemented")
// return super.isShown()
    }

    override fun isSoundEffectsEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isSoundEffectsEnabled()
    }



    override fun isTextAlignmentResolved(): Boolean {
//
        TODO("not implemented")
// return super.isTextAlignmentResolved()
    }

    override fun isTextDirectionResolved(): Boolean {
//
        TODO("not implemented")
// return super.isTextDirectionResolved()
    }

    override fun isVerticalFadingEdgeEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isVerticalFadingEdgeEnabled()
    }

    override fun isVerticalScrollBarEnabled(): Boolean {
//
        TODO("not implemented")
// return super.isVerticalScrollBarEnabled()
    }

    override fun jumpDrawablesToCurrentState() {
// Call Drawable.jumpToCurrentState()  on all Drawable objects associated with this view.
        TODO("not implemented")
        super.jumpDrawablesToCurrentState()
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
// Assign a size and position to a view and all of its  descendants
        TODO("not implemented")
// super.layout(l,t,r,b)
    }


    override fun offsetLeftAndRight(offset: Int) {
// Offset this view's horizontal location by the specified amount of pixels.
        TODO("not implemented")
// super.offsetLeftAndRight(offset)
    }

    override fun offsetTopAndBottom(offset: Int) {
// Offset this view's vertical location by the specified number of pixels.
        TODO("not implemented")
// super.offsetTopAndBottom(offset)
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
// Called when the view should apply WindowInsets according to its internal policy.
        TODO("not implemented")
// return super.onApplyWindowInsets(insets)
    }

    override fun onCancelPendingInputEvents() {
// Called as the result of a call to cancelPendingInputEvents() on this view or  a parent view.
        TODO("not implemented")
// super.onCancelPendingInputEvents()
    }

    override fun onCheckIsTextEditor(): Boolean {
// Check whether the called view is a text editor, in which case it  would make sense to automatically display a soft input window for  it.
        TODO("not implemented")
// return super.onCheckIsTextEditor()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
// Create a new InputConnection for an InputMethod to interact  with the view.
        TODO("not implemented")
// return super.onCreateInputConnection(outAttrs)
    }

    override fun onDragEvent(event: DragEvent): Boolean {
// Handles drag events sent by the system following a call to  startDragAndDrop().
        TODO("not implemented")
// return super.onDragEvent(event)
    }

    override fun onDrawForeground(canvas: Canvas) {
// Draw any foreground content for this view.
        TODO("not implemented")
// super.onDrawForeground(canvas)
    }

    override fun onFilterTouchEventForSecurity(event: MotionEvent): Boolean {
// Filter the touch event to apply security policies.
        TODO("not implemented")
// return super.onFilterTouchEventForSecurity(event)
    }

    override fun onFinishTemporaryDetach() {
// Called after onStartTemporaryDetach() when the container is done  changing the view.
        TODO("not implemented")
// super.onFinishTemporaryDetach()
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
// Implement this method to handle generic motion events.
        TODO("not implemented")
// return super.onGenericMotionEvent(event)
    }

    override fun onHoverChanged(hovered: Boolean) {
// Implement this method to handle hover state changes.
        TODO("not implemented")
// super.onHoverChanged(hovered)
    }

    override fun onHoverEvent(event: MotionEvent): Boolean {
// Implement this method to handle hover events.
        TODO("not implemented")
// return super.onHoverEvent(event)
    }

    override fun onInitializeAccessibilityEvent(event: AccessibilityEvent) {
// Initializes an AccessibilityEvent with information about  this View which is the event source.
        TODO("not implemented")
        super.onInitializeAccessibilityEvent(event)
    }

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo) {
// Initializes an AccessibilityNodeInfo with information about this view.
        TODO("not implemented")
        super.onInitializeAccessibilityNodeInfo(info)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyDown(): perform press of the view  when KEYCODE_DPAD_CENTER or KEYCODE_ENTER  is released, if the view is enabled and clickable.
        TODO("not implemented")
// return super.onKeyDown(keyCode,event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyLongPress(): always returns false (doesn't handle  the event).
        TODO("not implemented")
// return super.onKeyLongPress(keyCode,event)
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyMultiple(): always returns false (doesn't handle  the event).
        TODO("not implemented")
// return super.onKeyMultiple(keyCode,repeatCount,event)
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
// Handle a key event before it is processed by any input method  associated with the view hierarchy.
        TODO("not implemented")
// return super.onKeyPreIme(keyCode,event)
    }

    override fun onKeyShortcut(keyCode: Int, event: KeyEvent): Boolean {
// Called on the focused view when a key shortcut event is not handled.
        TODO("not implemented")
// return super.onKeyShortcut(keyCode,event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
// Default implementation of KeyEvent.Callback.onKeyUp(): perform clicking of the view  when KEYCODE_DPAD_CENTER, KEYCODE_ENTER  or KEYCODE_SPACE is released.
        TODO("not implemented")
// return super.onKeyUp(keyCode,event)
    }

    override fun onPopulateAccessibilityEvent(event: AccessibilityEvent) {
// Called from dispatchPopulateAccessibilityEvent(AccessibilityEvent)  giving a chance to this View to populate the accessibility event with its  text content.
        TODO("not implemented")
        super.onPopulateAccessibilityEvent(event)
    }

    override fun onProvideStructure(structure: ViewStructure) {
// Called when assist structure is being retrieved from a view as part of  Activity.onProvideAssistData.
        TODO("not implemented")
// super.onProvideStructure(structure)
    }

    override fun onProvideVirtualStructure(structure: ViewStructure) {
// Called when assist structure is being retrieved from a view as part of  Activity.onProvideAssistData to  generate additional virtual structure under this view.
        TODO("not implemented")
// super.onProvideVirtualStructure(structure)
    }

    override fun onResolvePointerIcon(event: MotionEvent, pointerIndex: Int): PointerIcon {
// Returns the pointer icon for the motion event, or null if it doesn't specify the icon.
        TODO("not implemented")
// return super.onResolvePointerIcon(event,pointerIndex)
    }

    override fun onRtlPropertiesChanged(layoutDirection: Int) {
// Called when any RTL property (layout direction or text direction or text alignment) has  been changed.
        TODO("not implemented")
// super.onRtlPropertiesChanged(layoutDirection)
    }

    override fun onScreenStateChanged(screenState: Int) {
// This method is called whenever the state of the screen this view is  attached to changes.
        TODO("not implemented")
// super.onScreenStateChanged(screenState)
    }

    override fun onStartTemporaryDetach() {
// This is called when a container is going to temporarily detach a child, with  ViewGroup.detachViewFromParent.
        TODO("not implemented")
// super.onStartTemporaryDetach()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
// Implement this method to handle touch screen motion events.
        TODO("not implemented")
// return super.onTouchEvent(event)
    }

    override fun onTrackballEvent(event: MotionEvent): Boolean {
// Implement this method to handle trackball motion events.
        TODO("not implemented")
// return super.onTrackballEvent(event)
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
// Called when the user-visibility of this View is potentially affected by a change  to this view itself, an ancestor view or the window this view is attached to.
        TODO("not implemented")
        super.onVisibilityAggregated(isVisible)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
// Called when the window containing this view gains or loses focus.
        TODO("not implemented")
// super.onWindowFocusChanged(hasWindowFocus)
    }

    override fun onWindowSystemUiVisibilityChanged(visible: Int) {
// Override to find out when the window's requested system UI visibility  has changed, that is the value returned by getWindowSystemUiVisibility().
        TODO("not implemented")
// super.onWindowSystemUiVisibilityChanged(visible)
    }

    override fun performAccessibilityAction(action: Int, arguments: Bundle): Boolean {
// Performs the specified accessibility action on the view.
        TODO("not implemented")
// return super.performAccessibilityAction(action,arguments)
    }

    override fun performClick(): Boolean {
// Call this view's OnClickListener, if it is defined.
        TODO("not implemented")
// return super.performClick()
    }

    override fun performContextClick(x: Float, y: Float): Boolean {
// Call this view's OnContextClickListener, if it is defined.
        TODO("not implemented")
// return super.performContextClick(x,y)
    }

    override fun performContextClick(): Boolean {
// Call this view's OnContextClickListener, if it is defined.
        TODO("not implemented")
// return super.performContextClick()
    }

    override fun performHapticFeedback(feedbackConstant: Int): Boolean {
// BZZZTT!!1!
        TODO("not implemented")
// return super.performHapticFeedback(feedbackConstant)
    }

    override fun performHapticFeedback(feedbackConstant: Int, flags: Int): Boolean {
// BZZZTT!!1!
        TODO("not implemented")
// return super.performHapticFeedback(feedbackConstant,flags)
    }

    override fun performLongClick(x: Float, y: Float): Boolean {
// Calls this view's OnLongClickListener, if it is defined.
        TODO("not implemented")
// return super.performLongClick(x,y)
    }

    override fun performLongClick(): Boolean {
// Calls this view's OnLongClickListener, if it is defined.
        TODO("not implemented")
// return super.performLongClick()
    }

    override fun playSoundEffect(soundConstant: Int) {
// Play a sound effect for this view.
        TODO("not implemented")
// super.playSoundEffect(soundConstant)
    }

    override fun post(action: Runnable): Boolean {
//
        TODO("not implemented")
// return super.post(action)
    }

    override fun postDelayed(action: Runnable, delayMillis: Long): Boolean {
//
        TODO("not implemented")
// return super.postDelayed(action,delayMillis)
    }

    override fun postInvalidate() {
//
        TODO("not implemented")
// super.postInvalidate()
    }

    override fun postInvalidate(left: Int, top: Int, right: Int, bottom: Int) {
//
        TODO("not implemented")
// super.postInvalidate(left,top,right,bottom)
    }

    override fun postInvalidateDelayed(delayMilliseconds: Long, left: Int, top: Int, right: Int, bottom: Int) {
//
        TODO("not implemented")
// super.postInvalidateDelayed(delayMilliseconds,left,top,right,bottom)
    }

    override fun postInvalidateDelayed(delayMilliseconds: Long) {
//
        TODO("not implemented")
// super.postInvalidateDelayed(delayMilliseconds)
    }

    override fun postInvalidateOnAnimation(left: Int, top: Int, right: Int, bottom: Int) {
//
        TODO("not implemented")
// super.postInvalidateOnAnimation(left,top,right,bottom)
    }

    override fun postInvalidateOnAnimation() {
//
        TODO("not implemented")
// super.postInvalidateOnAnimation()
    }

    override fun postOnAnimation(action: Runnable) {
//
        TODO("not implemented")
// super.postOnAnimation(action)
    }

    override fun postOnAnimationDelayed(action: Runnable, delayMillis: Long) {
//
        TODO("not implemented")
// super.postOnAnimationDelayed(action,delayMillis)
    }

    override fun refreshDrawableState() {
// Call this to force a view to update its drawable state.
        TODO("not implemented")
// super.refreshDrawableState()
    }

    override fun removeCallbacks(action: Runnable): Boolean {
//
        TODO("not implemented")
// return super.removeCallbacks(action)
    }

    override fun removeOnAttachStateChangeListener(listener: View.OnAttachStateChangeListener) {
// Remove a listener for attach state changes.
        TODO("not implemented")
// super.removeOnAttachStateChangeListener(listener)
    }

    override fun removeOnLayoutChangeListener(listener: View.OnLayoutChangeListener) {
// Remove a listener for layout changes.
        TODO("not implemented")
// super.removeOnLayoutChangeListener(listener)
    }

    override fun requestApplyInsets() {
// Ask that a new dispatch of onApplyWindowInsets(WindowInsets) be performed.
        TODO("not implemented")
// super.requestApplyInsets()
    }

    override fun requestFitSystemWindows() {
// This method was deprecated       in API level 20.     Use requestApplyInsets() for newer platform versions.
        TODO("not implemented")
// super.requestFitSystemWindows()
    }


    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect): Boolean {
// Call this to try to give focus to a specific view or to one of its descendants  and give it hints about the direction and a specific rectangle that the focus  is coming from.
        TODO("not implemented")
// return super.requestFocus(direction,previouslyFocusedRect)
    }


    override fun requestLayout() {
// Call this when something has changed which has invalidated the  layout of this view.
        TODO("not implemented")
        super.requestLayout()
    }

    override fun requestRectangleOnScreen(rectangle: Rect): Boolean {
// Request that a rectangle of this view be visible on the screen,  scrolling if necessary just enough.
        TODO("not implemented")
// return super.requestRectangleOnScreen(rectangle)
    }

    override fun requestRectangleOnScreen(rectangle: Rect, immediate: Boolean): Boolean {
// Request that a rectangle of this view be visible on the screen,  scrolling if necessary just enough.
        TODO("not implemented")
// return super.requestRectangleOnScreen(rectangle,immediate)
    }


    override fun restoreHierarchyState(container: SparseArray<Parcelable>) {
// Restore this view hierarchy's frozen state from the given container.
        TODO("not implemented")
// super.restoreHierarchyState(container)
    }

    override fun saveHierarchyState(container: SparseArray<Parcelable>) {
// Store this view hierarchy's frozen state into the given container.
        TODO("not implemented")
// super.saveHierarchyState(container)
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, whenx: Long) {
// Schedules an action on a drawable to occur at a specified time.
        TODO("not implemented")
// super.scheduleDrawable(who,what,when)
    }

    override fun scrollBy(x: Int, y: Int) {
// Move the scrolled position of your view.
        TODO("not implemented")
// super.scrollBy(x,y)
    }

    override fun scrollTo(x: Int, y: Int) {
// Set the scrolled position of your view.
        TODO("not implemented")
// super.scrollTo(x,y)
    }

    override fun sendAccessibilityEvent(eventType: Int) {
// Sends an accessibility event of the given type.
        TODO("not implemented")
// super.sendAccessibilityEvent(eventType)
    }

    override fun sendAccessibilityEventUnchecked(event: AccessibilityEvent) {
// This method behaves exactly as sendAccessibilityEvent(int) but  takes as an argument an empty AccessibilityEvent and does not  perform a check whether accessibility is enabled.
        TODO("not implemented")
// super.sendAccessibilityEventUnchecked(event)
    }

    override fun setAccessibilityDelegate(delegate: View.AccessibilityDelegate) {
// Sets a delegate for implementing accessibility support via composition  (as opposed to inheritance).
        TODO("not implemented")
// super.setAccessibilityDelegate(delegate)
    }

    override fun setAccessibilityLiveRegion(mode: Int) {
// Sets the live region mode for this view.
        TODO("not implemented")
// super.setAccessibilityLiveRegion(mode)
    }

    override fun setAccessibilityTraversalAfter(afterId: Int) {
// Sets the id of a view after which this one is visited in accessibility traversal.
        TODO("not implemented")
// super.setAccessibilityTraversalAfter(afterId)
    }

    override fun setAccessibilityTraversalBefore(beforeId: Int) {
// Sets the id of a view before which this one is visited in accessibility traversal.
        TODO("not implemented")
// super.setAccessibilityTraversalBefore(beforeId)
    }

    override fun setActivated(activated: Boolean) {
// Changes the activated state of this view.
        TODO("not implemented")
// super.setActivated(activated)
    }

    override fun setAlpha(alpha: Float) {
// Sets the opacity of the view to a value from 0 to 1, where 0 means the view is  completely transparent and 1 means the view is completely opaque.
        TODO("not implemented")
// super.setAlpha(alpha)
    }

    override fun setAnimation(animation: Animation) {
// Sets the next animation to play for this view.
        TODO("not implemented")
// super.setAnimation(animation)
    }

    override fun setBackground(background: Drawable) {
// Set the background to a given Drawable, or remove the background.
        TODO("not implemented")
// super.setBackground(background)
    }

    override fun setBackgroundColor(color: Int) {
// Sets the background color for this view.
        TODO("not implemented")
// super.setBackgroundColor(color)
    }

    override fun setBackgroundDrawable(background: Drawable) {
// This method was deprecated       in API level 16.     use setBackground(Drawable) instead
        TODO("not implemented")
// super.setBackgroundDrawable(background)
    }

    override fun setBackgroundResource(resid: Int) {
// Set the background to a given resource.
        TODO("not implemented")
// super.setBackgroundResource(resid)
    }

    override fun setBackgroundTintList(tint: ColorStateList) {
// Applies a tint to the background drawable.
        TODO("not implemented")
// super.setBackgroundTintList(tint)
    }

    override fun setBackgroundTintMode(tintMode: PorterDuff.Mode) {
// Specifies the blending mode used to apply the tint specified by  setBackgroundTintList(ColorStateList)} to the background  drawable.
        TODO("not implemented")
// super.setBackgroundTintMode(tintMode)
    }



    override fun setCameraDistance(distance: Float) {
//
        TODO("not implemented")
// super.setCameraDistance(distance)
    }

    override fun setClickable(clickable: Boolean) {
// Enables or disables click events for this view.
        TODO("not implemented")
// super.setClickable(clickable)
    }

    override fun setClipBounds(clipBounds: Rect) {
// Sets a rectangular area on this view to which the view will be clipped  when it is drawn.
        TODO("not implemented")
// super.setClipBounds(clipBounds)
    }

    override fun setClipToOutline(clipToOutline: Boolean) {
// Sets whether the View's Outline should be used to clip the contents of the View.
        TODO("not implemented")
// super.setClipToOutline(clipToOutline)
    }

    override fun setContentDescription(contentDescription: CharSequence) {
// Sets the View's content description.
        TODO("not implemented")
// super.setContentDescription(contentDescription)
    }

    override fun setContextClickable(contextClickable: Boolean) {
// Enables or disables context clicking for this view.
        TODO("not implemented")
// super.setContextClickable(contextClickable)
    }

    override fun setDrawingCacheBackgroundColor(color: Int) {
// Setting a solid background color for the drawing cache's bitmaps will improve  performance and memory usage.
        TODO("not implemented")
// super.setDrawingCacheBackgroundColor(color)
    }

    override fun setDrawingCacheEnabled(enabled: Boolean) {
//
        TODO("not implemented")
// super.setDrawingCacheEnabled(enabled)
    }

    override fun setDrawingCacheQuality(quality: Int) {
// Set the drawing cache quality of this view.
        TODO("not implemented")
// super.setDrawingCacheQuality(quality)
    }

    override fun setDuplicateParentStateEnabled(enabled: Boolean) {
//
        TODO("not implemented")
// super.setDuplicateParentStateEnabled(enabled)
    }

    override fun setElevation(elevation: Float) {
// Sets the base elevation of this view, in pixels.
        TODO("not implemented")
// super.setElevation(elevation)
    }

    override fun setEnabled(enabled: Boolean) {
// Set the enabled state of this view.
        TODO("not implemented")
// super.setEnabled(enabled)
    }

    override fun setFadingEdgeLength(length: Int) {
// Set the size of the faded edge used to indicate that more content in this  view is available.
        TODO("not implemented")
// super.setFadingEdgeLength(length)
    }

    override fun setFilterTouchesWhenObscured(enabled: Boolean) {
// Sets whether the framework should discard touches when the view's  window is obscured by another visible window.
        TODO("not implemented")
// super.setFilterTouchesWhenObscured(enabled)
    }

    override fun setFitsSystemWindows(fitSystemWindows: Boolean) {
// Sets whether or not this view should account for system screen decorations  such as the status bar and inset its content; that is, controlling whether  the default implementation of fitSystemWindows(Rect) will be  executed.
        TODO("not implemented")
// super.setFitsSystemWindows(fitSystemWindows)
    }

    override fun setFocusable(focusable: Boolean) {
// Set whether this view can receive the focus.
        TODO("not implemented")
// super.setFocusable(focusable)
    }

    override fun setFocusableInTouchMode(focusableInTouchMode: Boolean) {
// Set whether this view can receive focus while in touch mode.
        TODO("not implemented")
// super.setFocusableInTouchMode(focusableInTouchMode)
    }

    override fun setForeground(foreground: Drawable) {
// Supply a Drawable that is to be rendered on top of all of the content in the view.
        TODO("not implemented")
// super.setForeground(foreground)
    }

    override fun setForegroundGravity(gravity: Int) {
// Describes how the foreground is positioned.
        TODO("not implemented")
// super.setForegroundGravity(gravity)
    }

    override fun setForegroundTintList(tint: ColorStateList) {
// Applies a tint to the foreground drawable.
        TODO("not implemented")
// super.setForegroundTintList(tint)
    }

    override fun setForegroundTintMode(tintMode: PorterDuff.Mode) {
// Specifies the blending mode used to apply the tint specified by  setForegroundTintList(ColorStateList)} to the background  drawable.
        TODO("not implemented")
// super.setForegroundTintMode(tintMode)
    }

    override fun setHapticFeedbackEnabled(hapticFeedbackEnabled: Boolean) {
// Set whether this view should have haptic feedback for events such as  long presses.
        TODO("not implemented")
// super.setHapticFeedbackEnabled(hapticFeedbackEnabled)
    }

    override fun setHasTransientState(hasTransientState: Boolean) {
// Set whether this view is currently tracking transient state that the  framework should attempt to preserve when possible.
        TODO("not implemented")
// super.setHasTransientState(hasTransientState)
    }

    override fun setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled: Boolean) {
//
        TODO("not implemented")
// super.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled)
    }

    override fun setHorizontalScrollBarEnabled(horizontalScrollBarEnabled: Boolean) {
//
        TODO("not implemented")
// super.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled)
    }

    override fun setHovered(hovered: Boolean) {
// Sets whether the view is currently hovered.
        TODO("not implemented")
// super.setHovered(hovered)
    }

    override fun setId(id: Int) {
// Sets the identifier for this view.
        TODO("not implemented")
// super.setId(id)
    }

    override fun setImportantForAccessibility(mode: Int) {
// Sets how to determine whether this view is important for accessibility  which is if it fires accessibility events and if it is reported to  accessibility services that query the screen.
        TODO("not implemented")
// super.setImportantForAccessibility(mode)
    }

    override fun setKeepScreenOn(keepScreenOn: Boolean) {
// Controls whether the screen should remain on, modifying the  value of KEEP_SCREEN_ON.
        TODO("not implemented")
// super.setKeepScreenOn(keepScreenOn)
    }

    override fun setLabelFor(id: Int) {
// Sets the id of a view for which this view serves as a label for  accessibility purposes.
        TODO("not implemented")
// super.setLabelFor(id)
    }

    override fun setLayerPaint(paint: Paint) {
// Updates the Paint object used with the current layer (used only if the current  layer type is not set to LAYER_TYPE_NONE).
        TODO("not implemented")
// super.setLayerPaint(paint)
    }

    override fun setLayerType(layerType: Int, paint: Paint) {
//
        TODO("not implemented")
// super.setLayerType(layerType,paint)
    }

    override fun setLayoutDirection(layoutDirection: Int) {
// Set the layout direction for this view.
        TODO("not implemented")
// super.setLayoutDirection(layoutDirection)
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
// Set the layout parameters associated with this view.
        TODO("not implemented")
// super.setLayoutParams(params)
    }


    override fun setLongClickable(longClickable: Boolean) {
// Enables or disables long click events for this view.
        TODO("not implemented")
// super.setLongClickable(longClickable)
    }

    override fun setMinimumHeight(minHeight: Int) {
// Sets the minimum height of the view.
        TODO("not implemented")
// super.setMinimumHeight(minHeight)
    }

    override fun setMinimumWidth(minWidth: Int) {
// Sets the minimum width of the view.
        TODO("not implemented")
// super.setMinimumWidth(minWidth)
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
// Enable or disable nested scrolling for this view.
        TODO("not implemented")
// super.setNestedScrollingEnabled(enabled)
    }

    override fun setNextFocusDownId(nextFocusDownId: Int) {
// Sets the id of the view to use when the next focus is FOCUS_DOWN.
        TODO("not implemented")
// super.setNextFocusDownId(nextFocusDownId)
    }

    override fun setNextFocusForwardId(nextFocusForwardId: Int) {
// Sets the id of the view to use when the next focus is FOCUS_FORWARD.
        TODO("not implemented")
// super.setNextFocusForwardId(nextFocusForwardId)
    }

    override fun setNextFocusLeftId(nextFocusLeftId: Int) {
// Sets the id of the view to use when the next focus is FOCUS_LEFT.
        TODO("not implemented")
// super.setNextFocusLeftId(nextFocusLeftId)
    }

    override fun setNextFocusRightId(nextFocusRightId: Int) {
// Sets the id of the view to use when the next focus is FOCUS_RIGHT.
        TODO("not implemented")
// super.setNextFocusRightId(nextFocusRightId)
    }

    override fun setNextFocusUpId(nextFocusUpId: Int) {
// Sets the id of the view to use when the next focus is FOCUS_UP.
        TODO("not implemented")
// super.setNextFocusUpId(nextFocusUpId)
    }

    override fun setOnApplyWindowInsetsListener(listener: View.OnApplyWindowInsetsListener) {
// Set an View.OnApplyWindowInsetsListener to take over the policy for applying  window insets to this view.
        TODO("not implemented")
// super.setOnApplyWindowInsetsListener(listener)
    }

    override fun setOnClickListener(l: View.OnClickListener) {
// Register a callback to be invoked when this view is clicked.
        TODO("not implemented")
// super.setOnClickListener(l)
    }

    override fun setOnContextClickListener(l: View.OnContextClickListener) {
// Register a callback to be invoked when this view is context clicked.
        TODO("not implemented")
// super.setOnContextClickListener(l)
    }

    override fun setOnCreateContextMenuListener(l: View.OnCreateContextMenuListener) {
// Register a callback to be invoked when the context menu for this view is  being built.
        TODO("not implemented")
// super.setOnCreateContextMenuListener(l)
    }

    override fun setOnDragListener(l: View.OnDragListener) {
// Register a drag event listener callback object for this View.
        TODO("not implemented")
// super.setOnDragListener(l)
    }

    override fun setOnFocusChangeListener(l: View.OnFocusChangeListener) {
// Register a callback to be invoked when focus of this view changed.
        TODO("not implemented")
// super.setOnFocusChangeListener(l)
    }

    override fun setOnGenericMotionListener(l: View.OnGenericMotionListener) {
// Register a callback to be invoked when a generic motion event is sent to this view.
        TODO("not implemented")
// super.setOnGenericMotionListener(l)
    }

    override fun setOnHoverListener(l: View.OnHoverListener) {
// Register a callback to be invoked when a hover event is sent to this view.
        TODO("not implemented")
// super.setOnHoverListener(l)
    }

    override fun setOnKeyListener(l: View.OnKeyListener) {
// Register a callback to be invoked when a hardware key is pressed in this view.
        TODO("not implemented")
// super.setOnKeyListener(l)
    }

    override fun setOnLongClickListener(l: View.OnLongClickListener) {
// Register a callback to be invoked when this view is clicked and held.
        TODO("not implemented")
// super.setOnLongClickListener(l)
    }

    override fun setOnScrollChangeListener(l: View.OnScrollChangeListener) {
// Register a callback to be invoked when the scroll X or Y positions of  this view change.
        TODO("not implemented")
// super.setOnScrollChangeListener(l)
    }

    override fun setOnSystemUiVisibilityChangeListener(l: View.OnSystemUiVisibilityChangeListener) {
// Set a listener to receive callbacks when the visibility of the system bar changes.
        TODO("not implemented")
// super.setOnSystemUiVisibilityChangeListener(l)
    }

    override fun setOnTouchListener(l: View.OnTouchListener) {
// Register a callback to be invoked when a touch event is sent to this view.
        TODO("not implemented")
// super.setOnTouchListener(l)
    }

    override fun setOutlineProvider(provider: ViewOutlineProvider) {
// Sets the ViewOutlineProvider of the view, which generates the Outline that defines  the shape of the shadow it casts, and enables outline clipping.
        TODO("not implemented")
// super.setOutlineProvider(provider)
    }

    override fun setOverScrollMode(overScrollMode: Int) {
// Set the over-scroll mode for this view.
        TODO("not implemented")
// super.setOverScrollMode(overScrollMode)
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
// Sets the padding.
        TODO("not implemented")
// super.setPadding(left,top,right,bottom)
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
// Sets the relative padding.
        TODO("not implemented")
// super.setPaddingRelative(start,top,end,bottom)
    }

    override fun setPivotX(pivotX: Float) {
// Sets the x location of the point around which the view is  rotated and scaled.
        TODO("not implemented")
// super.setPivotX(pivotX)
    }

    override fun setPivotY(pivotY: Float) {
// Sets the y location of the point around which the view is rotated  and scaled.
        TODO("not implemented")
// super.setPivotY(pivotY)
    }

    override fun setPointerIcon(pointerIcon: PointerIcon) {
// Set the pointer icon for the current view.
        TODO("not implemented")
// super.setPointerIcon(pointerIcon)
    }

    override fun setPressed(pressed: Boolean) {
// Sets the pressed state for this view.
        TODO("not implemented")
// super.setPressed(pressed)
    }


    override fun setRotation(rotation: Float) {
// Sets the degrees that the view is rotated around the pivot point.
        TODO("not implemented")
// super.setRotation(rotation)
    }

    override fun setRotationX(rotationX: Float) {
// Sets the degrees that the view is rotated around the horizontal axis through the pivot point.
        TODO("not implemented")
// super.setRotationX(rotationX)
    }

    override fun setRotationY(rotationY: Float) {
// Sets the degrees that the view is rotated around the vertical axis through the pivot point.
        TODO("not implemented")
// super.setRotationY(rotationY)
    }

    override fun setSaveEnabled(enabled: Boolean) {
// Controls whether the saving of this view's state is  enabled (that is, whether its onSaveInstanceState() method  will be called).
        TODO("not implemented")
// super.setSaveEnabled(enabled)
    }

    override fun setSaveFromParentEnabled(enabled: Boolean) {
// Controls whether the entire hierarchy under this view will save its  state when a state saving traversal occurs from its parent.
        TODO("not implemented")
// super.setSaveFromParentEnabled(enabled)
    }

    override fun setScaleX(scaleX: Float) {
// Sets the amount that the view is scaled in x around the pivot point, as a proportion of  the view's unscaled width.
        TODO("not implemented")
// super.setScaleX(scaleX)
    }

    override fun setScaleY(scaleY: Float) {
// Sets the amount that the view is scaled in Y around the pivot point, as a proportion of  the view's unscaled width.
        TODO("not implemented")
// super.setScaleY(scaleY)
    }

    override fun setScrollBarDefaultDelayBeforeFade(scrollBarDefaultDelayBeforeFade: Int) {
// Define the delay before scrollbars fade.
        TODO("not implemented")
// super.setScrollBarDefaultDelayBeforeFade(scrollBarDefaultDelayBeforeFade)
    }

    override fun setScrollBarFadeDuration(scrollBarFadeDuration: Int) {
// Define the scrollbar fade duration.
        TODO("not implemented")
// super.setScrollBarFadeDuration(scrollBarFadeDuration)
    }

    override fun setScrollBarSize(scrollBarSize: Int) {
// Define the scrollbar size.
        TODO("not implemented")
// super.setScrollBarSize(scrollBarSize)
    }

    override fun setScrollBarStyle(style: Int) {
//
        TODO("not implemented")
// super.setScrollBarStyle(style)
    }

    override fun setScrollContainer(isScrollContainer: Boolean) {
// Change whether this view is one of the set of scrollable containers in  its window.
        TODO("not implemented")
// super.setScrollContainer(isScrollContainer)
    }

    override fun setScrollIndicators(indicators: Int, mask: Int) {
// Sets the state of the scroll indicators specified by the mask.
        TODO("not implemented")
// super.setScrollIndicators(indicators,mask)
    }

    override fun setScrollIndicators(indicators: Int) {
// Sets the state of all scroll indicators.
        TODO("not implemented")
// super.setScrollIndicators(indicators)
    }

    override fun setScrollX(value: Int) {
// Set the horizontal scrolled position of your view.
        TODO("not implemented")
// super.setScrollX(value)
    }

    override fun setScrollY(value: Int) {
// Set the vertical scrolled position of your view.
        TODO("not implemented")
// super.setScrollY(value)
    }

    override fun setScrollbarFadingEnabled(fadeScrollbars: Boolean) {
// Define whether scrollbars will fade when the view is not scrolling.
        TODO("not implemented")
// super.setScrollbarFadingEnabled(fadeScrollbars)
    }

    override fun setSelected(selected: Boolean) {
// Changes the selection state of this view.
        TODO("not implemented")
// super.setSelected(selected)
    }

    override fun setSoundEffectsEnabled(soundEffectsEnabled: Boolean) {
// Set whether this view should have sound effects enabled for events such as  clicking and touching.
        TODO("not implemented")
// super.setSoundEffectsEnabled(soundEffectsEnabled)
    }

    override fun setStateListAnimator(stateListAnimator: StateListAnimator) {
// Attaches the provided StateListAnimator to this View.
        TODO("not implemented")
// super.setStateListAnimator(stateListAnimator)
    }

    override fun setSystemUiVisibility(visibility: Int) {
// Request that the visibility of the status bar or other screen/window  decorations be changed.
        TODO("not implemented")
// super.setSystemUiVisibility(visibility)
    }



    override fun setTextAlignment(textAlignment: Int) {
// Set the text alignment.
        TODO("not implemented")
// super.setTextAlignment(textAlignment)
    }

    override fun setTextDirection(textDirection: Int) {
// Set the text direction.
        TODO("not implemented")
// super.setTextDirection(textDirection)
    }


    override fun setTouchDelegate(delegate: TouchDelegate) {
// Sets the TouchDelegate for this View.
        TODO("not implemented")
// super.setTouchDelegate(delegate)
    }



    override fun setTranslationX(translationX: Float) {
// Sets the horizontal location of this view relative to its left position.
        TODO("not implemented")
// super.setTranslationX(translationX)
    }

    override fun setTranslationY(translationY: Float) {
// Sets the vertical location of this view relative to its top position.
        TODO("not implemented")
// super.setTranslationY(translationY)
    }

    override fun setTranslationZ(translationZ: Float) {
// Sets the depth location of this view relative to its elevation.
        TODO("not implemented")
// super.setTranslationZ(translationZ)
    }

    override fun setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled: Boolean) {
//
        TODO("not implemented")
// super.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled)
    }

    override fun setVerticalScrollBarEnabled(verticalScrollBarEnabled: Boolean) {
//
        TODO("not implemented")
// super.setVerticalScrollBarEnabled(verticalScrollBarEnabled)
    }

    override fun setVerticalScrollbarPosition(position: Int) {
// Set the position of the vertical scroll bar.
        TODO("not implemented")
// super.setVerticalScrollbarPosition(position)
    }

    override fun setVisibility(visibility: Int) {
// Set the enabled state of this view.
        TODO("not implemented")
// super.setVisibility(visibility)
    }

    override fun setWillNotCacheDrawing(willNotCacheDrawing: Boolean) {
// When a View's drawing cache is enabled, drawing is redirected to an  offscreen bitmap.
        TODO("not implemented")
// super.setWillNotCacheDrawing(willNotCacheDrawing)
    }

    override fun setWillNotDraw(willNotDraw: Boolean) {
// If this view doesn't do any drawing on its own, set this flag to  allow further optimizations.
        TODO("not implemented")
// super.setWillNotDraw(willNotDraw)
    }

    override fun setX(x: Float) {
// Sets the visual x position of this view, in pixels.
        TODO("not implemented")
// super.setX(x)
    }

    override fun setY(y: Float) {
// Sets the visual y position of this view, in pixels.
        TODO("not implemented")
// super.setY(y)
    }

    override fun setZ(z: Float) {
// Sets the visual z position of this view, in pixels.
        TODO("not implemented")
// super.setZ(z)
    }

    override fun showContextMenu(): Boolean {
// Shows the context menu for this view.
        TODO("not implemented")
// return super.showContextMenu()
    }

    override fun showContextMenu(x: Float, y: Float): Boolean {
// Shows the context menu for this view anchored to the specified  view-relative coordinate.
        TODO("not implemented")
// return super.showContextMenu(x,y)
    }

    override fun startActionMode(callback: ActionMode.Callback, type: Int): ActionMode {
// Start an action mode with the given type.
        TODO("not implemented")
// return super.startActionMode(callback,type)
    }

    override fun startActionMode(callback: ActionMode.Callback): ActionMode {
// Start an action mode with the default type TYPE_PRIMARY.
        TODO("not implemented")
// return super.startActionMode(callback)
    }

    override fun startAnimation(animation: Animation) {
// Start the specified animation now.
        TODO("not implemented")
// super.startAnimation(animation)
    }


    override fun startNestedScroll(axes: Int): Boolean {
// Begin a nestable scroll operation along the given axes.
        TODO("not implemented")
// return super.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
// Stop a nested scroll in progress.
        TODO("not implemented")
// super.stopNestedScroll()
    }

    override fun toString(): String {
// Returns a string representation of the object.
        TODO("not implemented")
// return super.toString()
    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
// Cancels a scheduled action on a drawable.
        TODO("not implemented")
// super.unscheduleDrawable(who,what)
    }

    override fun unscheduleDrawable(who: Drawable) {
// Unschedule any events associated with the given Drawable.
        TODO("not implemented")
// super.unscheduleDrawable(who)
    }


    override fun willNotCacheDrawing(): Boolean {
// Returns whether or not this View can cache its drawing or not.
        TODO("not implemented")
// return super.willNotCacheDrawing()
    }

    override fun willNotDraw(): Boolean {
// Returns whether or not this View draws on its own.
        TODO("not implemented")
// return super.willNotDraw()
    }

}