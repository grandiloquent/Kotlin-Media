package euphoria.psycho.translator

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import euphoria.psycho.common.getDefaultSharedPreferences
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.actionbar_translate.*
import kotlinx.android.synthetic.main.fragment_translate.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class TranslatorFragment : Fragment() {

    private var mTargetLanguage = LANGUAGE_EN
    private var mSourceLanguage = LANGUAGE_AUTO
    private lateinit var mHandlerThread: HandlerThread
    private lateinit var mHandler: Handler

    private val mHandlerCallback = Handler.Callback { message ->
        when (message.what) {
            MSG_QUERY -> {
                query(message.obj as String)
            }
        }
        true
    }

    private fun generateTranslateURL(str: String, targetLanguage: String, sourceLanguage: String = "auto"): String {
        return "https://translate.google.cn/translate_a/single?client=gtx&sl=$sourceLanguage&tl=$targetLanguage&dt=t&dt=bd&ie=UTF-8&oe=UTF-8&dj=1&source=icon&q=${Uri.decode(
            str
        )}";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHandlerThread = HandlerThread(TAG)
        mHandlerThread.start()
        mHandler = Handler(mHandlerThread.looper, mHandlerCallback)
    }

    private fun initialize() {
        val preferences = requireContext().getDefaultSharedPreferences()
        mTargetLanguage = preferences.getString(KEY_TARGET_LANGUAGE, LANGUAGE_EN)
        mSourceLanguage = preferences.getString(KEY_SOURCE_LANGUAGE, LANGUAGE_AUTO)
        when (mSourceLanguage) {
            LANGUAGE_AUTO -> uiTextViewSourceLanguage.text = "语言自动检测"
            LANGUAGE_EN -> uiTextViewSourceLanguage.text = "英文"
        }
        when (mTargetLanguage) {
            LANGUAGE_EN -> uiTextViewTargetLanguage.text = "英文"
            else -> uiTextViewTargetLanguage.text = "中文"
        }
        uiImageViewSwitcher.setOnClickListener {
            val tmp = mTargetLanguage;
            mTargetLanguage = mSourceLanguage
            mSourceLanguage = tmp
            val tmpText = uiTextViewSourceLanguage.text
            uiTextViewSourceLanguage.text = uiTextViewTargetLanguage.text
            uiTextViewTargetLanguage.text = tmpText;
        }
        uiTextViewTargetLanguage.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                menuInflater.inflate(R.menu.menu_language, menu)
                setOnMenuItemClickListener { item: MenuItem ->
                    when (item.itemId) {
                        R.id.action_language_zh -> {
                        }
                    }
                    true
                }
                show()
            }
        }
        uiImageViewClear.setOnClickListener {
            uiEditText.text = null
        }
        uiTextViewTranslatePhoto.setOnClickListener {
            val str = uiEditText.text
            if (!str.isNullOrBlank()) {
                mHandler.sendMessage(mHandler.obtainMessage(MSG_QUERY, str.toString()))
            }
        }
        uiEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (uiEditText.text.isNullOrEmpty()) {
                    uiImageViewClear.visibility = View.GONE
                } else {
                    uiImageViewClear.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    override fun onStop() {
        super.onStop()
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null)
        }
        if (mHandlerThread != null) {
            mHandlerThread.quit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_translate, container, false)
    }

    private fun parseJSON(str: String): String? {
        val obj = JSONObject(str)
        if (obj.has("sentences")) {
            val sentences = obj.getJSONArray("sentences")
            if (sentences.length() < 1) return null
            val sb = StringBuilder()
            for (i in 0 until sentences.length()) {
                sb.append(sentences.getJSONObject(i).getString("trans"))
            }
            return sb.toString()
        }
        return null
    }

    private fun query(str: String) {
        try {
            val url = generateTranslateURL(str, mTargetLanguage, mSourceLanguage)
            val request = Request.Builder()
                .url(url).build()
            val res = OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .build().newCall(request).execute()
            if (res.isSuccessful) {
                res.body()?.let {
                    uiTextViewTranslateResult.text = parseJSON(it.string())
                }
            } else {
                uiTextViewTranslateResult.text = res.message()
            }
        } catch (e: Exception) {
            uiTextViewTranslateResult.setText(e.message)
        }
    }

    companion object {
        private const val TAG = "TranslatorFragment"
        private const val LANGUAGE_AUTO = "auto"
        private const val LANGUAGE_ZH = "zh"
        private const val LANGUAGE_EN = "en"
        private const val KEY_TARGET_LANGUAGE = "target_language"
        private const val KEY_SOURCE_LANGUAGE = "source_language"
        private const val MSG_QUERY = 1
        private const val TIME_OUT = 1000 * 20L

    }
}