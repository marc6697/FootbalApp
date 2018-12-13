package marc6697com.marc6697.footbalmatchschedulefix.NextMatch



interface NM_View {
    fun showProgressBar()
    fun hideProgressBar()
    fun showNextMatchList(data: List<NM_Model_NextMatch>)
}