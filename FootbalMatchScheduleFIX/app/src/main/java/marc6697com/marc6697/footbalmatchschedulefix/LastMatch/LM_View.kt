package marc6697com.marc6697.footbalmatchschedulefix.LastMatch

interface LM_View {
    fun showProgressBar()
    fun hideProgressBar()
    fun showLastMatchList(data: List<LM_Model_LastMatch>)
}