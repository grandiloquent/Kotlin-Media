package euphoria.psycho.translator

import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText


class MultiEditText : AppCompatEditText {
    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initialize()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleRes: Int) : super(
        context,
        attributeSet,
        defStyleRes
    ) {
        initialize()
    }

    private fun initialize() {
        setHorizontallyScrolling(false)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val inputConnection = super.onCreateInputConnection(outAttrs)
        val i = outAttrs.imeOptions and 0xFF
        if (i and 0x3 !== 0) {
            outAttrs.imeOptions = i xor outAttrs.imeOptions
            outAttrs.imeOptions = outAttrs.imeOptions or 0x3
        }
        if (outAttrs.imeOptions and 0x40000000 !== 0) {
            outAttrs.imeOptions = outAttrs.imeOptions and -0x40000001
        }
        return inputConnection
    }
}