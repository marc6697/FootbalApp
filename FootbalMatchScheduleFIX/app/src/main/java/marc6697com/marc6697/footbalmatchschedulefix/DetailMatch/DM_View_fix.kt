package marc6697com.marc6697.footbalmatchschedulefix.DetailMatch

import android.widget.ProgressBar




interface DM_View_fix {
    fun showProgressBar(progressBar: ProgressBar)
    fun hideProgressBar(progressBar: ProgressBar)
    fun showMatchDetail(data:List<DM_Model_Match>)
    fun showHomeLogo(data:List<DM_Model_Logo>)
    fun showAwayLogo(data:List<DM_Model_Logo>)
}
