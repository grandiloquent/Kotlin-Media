package euphoria.psycho.media

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.RadioGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_sort_layout.*

class SortDialog : DialogFragment(),
    RadioGroup.OnCheckedChangeListener {
    private var mSortId = 0
    private var mSequenceId = 0

    var onCheckedChanged: ((Int, Int) -> Unit)? = null

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (group?.id == R.id.uiRadioGroupSort) {
            mSortId = when (checkedId) {
                R.id.uiRadioButtonSortDefault -> SORT_BY_DEFAULT
                R.id.uiRadioButtonSortDate -> SORT_BY_DATE
                R.id.uiRadioButtonSortName -> SORT_BY_NAME
                R.id.uiRadioButtonSortSize -> SORT_BY_SIZE
                else -> SORT_BY_DEFAULT
            }
        } else {
            mSequenceId = when (checkedId) {
                R.id.uiRadioButtonSequenceAsc -> SORT_ASC
                else -> SORT_DESC
            }
        }
    }


    override fun onStart() {
        super.onStart()
        context?.let {
            val width = it.resources.displayMetrics.widthPixels * 0.88f
            dialog.window.setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_sort_layout, container, false)
    }

    fun onBackPressed(): Boolean {
        return true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(context, R.style.Common_Dialog).apply { window.setGravity(Gravity.CENTER) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSortId = arguments?.getInt(KEY_SORT_ID, R.id.uiRadioButtonSortDefault) ?: 0
        mSequenceId = arguments?.getInt(KEY_SEQUENCE_ID, R.id.uiRadioButtonSequenceAsc) ?: 0
        uiLinearLayoutSequence.visibility = if (mSortId == R.id.uiRadioButtonSortDefault) View.GONE else View.VISIBLE

        when (mSortId) {
            SORT_BY_DEFAULT -> uiRadioButtonSortDefault.isChecked = true
            SORT_BY_DATE -> uiRadioButtonSortDate.isChecked = true
            SORT_BY_NAME -> uiRadioButtonSortName.isChecked = true
            SORT_BY_SIZE -> uiRadioButtonSortSize.isChecked = true
            else -> uiRadioButtonSortDefault.isChecked = true
        }
        when (mSequenceId) {
            SORT_DESC -> uiRadioButtonSequenceDesc.isChecked = true
            else -> uiRadioButtonSequenceAsc.isChecked = true
        }
        uiRadioGroupSort.setOnCheckedChangeListener(this)
        uiRadioGroupSequence.setOnCheckedChangeListener(this)
        uiTextViewCancel.setOnClickListener { dismiss() }
        uiButtonOK.setOnClickListener {
            onCheckedChanged?.invoke(mSortId, mSequenceId)
            dismiss()
        }
        dialog.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) onBackPressed()
            false
        }
    }

    companion object {
        const val SORT_BY_DEFAULT = 1 shl 1
        const val SORT_BY_NAME = 1 shl 2
        const val SORT_BY_DATE = 1 shl 3
        const val SORT_BY_SIZE = 1 shl 4
        const val SORT_ASC = 1 shl 1
        const val SORT_DESC = 1 shl 2
        const val KEY_SORT_ID = "sort_id"
        const val KEY_SEQUENCE_ID = "sequence_id"
        fun newInstance(sortId: Int, sequenceId: Int): SortDialog {
            return SortDialog().apply {
                arguments = Bundle().apply {
                    putInt(KEY_SORT_ID, sortId)
                    putInt(KEY_SEQUENCE_ID, sequenceId)
                }
            }
        }
    }
}