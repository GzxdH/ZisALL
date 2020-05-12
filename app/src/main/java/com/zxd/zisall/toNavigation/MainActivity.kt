package com.zxd.zisall.toNavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.navi.AMapNavi
import com.amap.api.navi.AMapNaviListener
import com.amap.api.navi.AMapNaviViewListener
import com.amap.api.navi.INaviInfoCallback
import com.amap.api.navi.enums.NaviType
import com.amap.api.navi.enums.SoundQuality
import com.amap.api.navi.model.*
import com.autonavi.tbt.TrafficFacilityInfo
import com.zxd.zisall.R
import com.zxd.zisall.toNavigation.xf.TTSController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AMapNaviViewListener, AMapNaviListener,
    INaviInfoCallback {

    lateinit var mAMapNavi: AMapNavi
    var TAG = "zAll"
    lateinit var mTtsManager: TTSController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navi_view.setAMapNaviViewListener(this)
        navi_view.onCreate(savedInstanceState)

        // 1 . 实例化语音引擎
        mTtsManager = TTSController.getInstance(this)
        mTtsManager.setTTSType(TTSController.TTSType.IFLYTTS)

        mAMapNavi = AMapNavi.getInstance(this)
        mAMapNavi.addAMapNaviListener(this)

        // 2. 添加导航监听
        mAMapNavi.addAMapNaviListener(mTtsManager)

        mAMapNavi.setUseInnerVoice(true)
        mAMapNavi.setSoundQuality(SoundQuality.High_Quality)

//        AmapNaviPage.getInstance().showRouteActivity(
//            applicationContext,
//            AmapNaviParams(null, null, null, AmapNaviType.WALK),
//            this@MainActivity
//        )

        //使用组件进行导航
//        AmapNaviPage.getInstance().showRouteActivity(this, AmapNaviParams(null, null, null, AmapNaviType.WALK), this@MainActivity)


    }

    override fun onResume() {
        super.onResume()
        navi_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        navi_view.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        navi_view.onDestroy()
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
        // 4. 退出 销毁
        mTtsManager.destroy();
    }

    override fun onNaviTurnClick() {
        Log.d(TAG, "--onNaviTurnClick")
    }

    override fun onScanViewButtonClick() {
        Log.d(TAG, "--onScanViewButtonClick")
    }

    override fun onLockMap(p0: Boolean) {
        Log.d(TAG, "--onLockMap" + p0)
    }

    override fun onMapTypeChanged(p0: Int) {
        Log.d(TAG, "--onMapTypeChanged" + p0)
    }

    override fun onNaviCancel() {
        Log.d(TAG, "--onNaviCancel")
        //导航页面左下角返回按钮点击后弹出的『退出导航对话框』中选择『确定』后的回调接口。
        finish();
    }

    override fun onNaviViewLoaded() {
        Log.d(TAG, "--onNaviViewLoaded")
    }

    override fun onNaviBackClick(): Boolean {
        Log.d(TAG, "--onNaviBackClick")
        //导航页面左下角返回按钮的回调接口
        //false-由SDK主动弹出『退出导航』对话框，
        //true-SDK不主动弹出『退出导航对话框』，由用户自定义
        return false
    }

    override fun onNaviMapMode(p0: Int) {
        Log.d(TAG, "--onNaviMapMode" + p0)
    }

    override fun onNextRoadClick() {
        Log.d(TAG, "--onNextRoadClick")
    }

    override fun onNaviViewShowMode(p0: Int) {
        Log.d(TAG, "--onNaviViewShowMode" + p0)
    }

    override fun onNaviSetting() {
        Log.d(TAG, "--onNaviSetting")
    }

    override fun onNaviInfoUpdate(p0: NaviInfo?) {
        Log.d(TAG, "--onNaviInfoUpdate" + p0)
    }

    override fun onCalculateRouteSuccess(p0: IntArray?) {
        Log.d(TAG, "--onCalculateRouteSuccess" + p0)
        mAMapNavi.startNavi(NaviType.EMULATOR);
    }

    override fun onCalculateRouteSuccess(p0: AMapCalcRouteResult?) {
        Log.d(TAG, "++onCalculateRouteSuccess" + p0)
        //模拟导航
//        mAMapNavi.startNavi(NaviType.EMULATOR);
    }

    override fun onCalculateRouteFailure(p0: Int) {
        Log.d(TAG, "--onCalculateRouteFailure" + p0)
    }

    override fun getCustomMiddleView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalculateRouteFailure(p0: AMapCalcRouteResult?) {
        Log.d(TAG, "++onCalculateRouteFailure" + p0)
    }

    override fun onServiceAreaUpdate(p0: Array<out AMapServiceAreaInfo>?) {
        Log.d(TAG, "--onServiceAreaUpdate" + p0)
    }

    override fun onEndEmulatorNavi() {
        Log.d(TAG, "--onEndEmulatorNavi")
    }

    override fun onArrivedWayPoint(p0: Int) {
        Log.d(TAG, "--onArrivedWayPoint" + p0)
    }

    override fun onArriveDestination(p0: Boolean) {
        Log.d(TAG, "--onArriveDestination" + p0)
    }

    override fun onArriveDestination() {
        Log.d(TAG, "--onArriveDestination")
    }

    override fun onPlayRing(p0: Int) {
        Log.d(TAG, "--onPlayRing" + p0)
    }

    override fun onTrafficStatusUpdate() {
        Log.d(TAG, "--onTrafficStatusUpdate")
    }

    override fun onGpsOpenStatus(p0: Boolean) {
        Log.d(TAG, "--onGpsOpenStatus" + p0)
    }

    override fun updateAimlessModeCongestionInfo(p0: AimLessModeCongestionInfo?) {
        Log.d(TAG, "--updateAimlessModeCongestionInfo" + p0)
    }

    override fun showCross(p0: AMapNaviCross?) {
        Log.d(TAG, "--showCross" + p0)
    }

    override fun onGetNavigationText(p0: Int, p1: String?) {
        Log.d(TAG, "--onGetNavigationText" + p0 + "--" + p1)
    }

    override fun onGetNavigationText(p0: String?) {
        Log.d(TAG, "--onGetNavigationText" + p0)
        // 3. 开启导航后在回调 onGetNavigationText(String s)
        // 将播报文字添加到播报队列中 参见 TTSController 的onGetNavigationText 回调 方法
    }

    override fun updateAimlessModeStatistics(p0: AimLessModeStat?) {
        Log.d(TAG, "--updateAimlessModeStatistics" + p0)
    }

    override fun hideCross() {
        Log.d(TAG, "--hideCross")
    }

    override fun onInitNaviFailure() {
        Log.d(TAG, "--onInitNaviFailure")
    }

    override fun onStrategyChanged(p0: Int) {
        Log.d(TAG, "--onStrategyChanged" + p0)
    }

    override fun onScaleAutoChanged(p0: Boolean) {
        Log.d(TAG, "--onScaleAutoChanged" + p0)
    }

    override fun onReCalculateRoute(p0: Int) {
        Log.d(TAG, "--onReCalculateRoute" + p0)
    }

    override fun getCustomNaviView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDayAndNightModeChanged(p0: Int) {
        Log.d(TAG, "--onDayAndNightModeChanged" + p0)
    }

    override fun onInitNaviSuccess() {
        Log.d(TAG, "--onInitNaviSuccess")
        val p1 = NaviLatLng(39.917337, 116.397056) //故宫博物院
        val p2 = NaviLatLng(39.904556, 116.427231) //北京站
        mAMapNavi.calculateRideRoute(p1, p2)
    }

    override fun onReCalculateRouteForTrafficJam() {
        Log.d(TAG, "--onReCalculateRouteForTrafficJam")
    }

    override fun updateIntervalCameraInfo(
        p0: AMapNaviCameraInfo?,
        p1: AMapNaviCameraInfo?,
        p2: Int
    ) {
        Log.d(TAG, "--updateIntervalCameraInfo" + p0 + "--" + p1 + "--" + p2)
    }

    override fun hideLaneInfo() {
        Log.d(TAG, "--hideLaneInfo")
    }

    override fun onNaviInfoUpdated(p0: AMapNaviInfo?) {
        Log.d(TAG, "--onNaviInfoUpdated" + p0)
    }

    override fun showModeCross(p0: AMapModelCross?) {
        Log.d(TAG, "--showModeCross" + p0)
    }

    override fun updateCameraInfo(p0: Array<out AMapNaviCameraInfo>?) {
        Log.d(TAG, "--updateCameraInfo" + p0)
    }

    override fun hideModeCross() {
        Log.d(TAG, "--hideModeCross")
    }

    override fun onLocationChange(p0: AMapNaviLocation?) {
        Log.d(TAG, "--onLocationChange" + p0)
    }

    override fun getCustomNaviBottomView(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReCalculateRouteForYaw() {
        Log.d(TAG, "--onReCalculateRouteForYaw")
    }

    override fun onStartNavi(p0: Int) {
        Log.d(TAG, "--onStartNavi" + p0)
    }

    override fun onStopSpeaking() {
        Log.d(TAG, "--onStopSpeaking")
    }

    override fun onExitPage(p0: Int) {
        Log.d(TAG, "--onExitPage" + p0)
    }

    override fun onNaviDirectionChanged(p0: Int) {
        Log.d(TAG, "--onNaviDirectionChanged" + p0)
    }

    override fun onBroadcastModeChanged(p0: Int) {
        Log.d(TAG, "--onBroadcastModeChanged" + p0)
    }

    override fun notifyParallelRoad(p0: Int) {
        Log.d(TAG, "--notifyParallelRoad" + p0)
    }

    override fun OnUpdateTrafficFacility(p0: Array<out AMapNaviTrafficFacilityInfo>?) {
        Log.d(TAG, "--OnUpdateTrafficFacility" + p0)
    }

    override fun OnUpdateTrafficFacility(p0: AMapNaviTrafficFacilityInfo?) {
        Log.d(TAG, "++OnUpdateTrafficFacility" + p0)
    }

    override fun OnUpdateTrafficFacility(p0: TrafficFacilityInfo?) {
        Log.d(TAG, "**OnUpdateTrafficFacility" + p0)
    }

    override fun onNaviRouteNotify(p0: AMapNaviRouteNotifyData?) {
        Log.d(TAG, "--onNaviRouteNotify" + p0)
    }

    override fun showLaneInfo(p0: Array<out AMapLaneInfo>?, p1: ByteArray?, p2: ByteArray?) {
        Log.d(TAG, "--showLaneInfo" + p0)
    }

    override fun showLaneInfo(p0: AMapLaneInfo?) {
        Log.d(TAG, "++showLaneInfo" + p0)
    }
}
