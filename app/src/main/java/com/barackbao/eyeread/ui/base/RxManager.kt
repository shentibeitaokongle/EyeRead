package com.barackbao.eyeread.ui.base

import io.reactivex.disposables.Disposable

/**
 * Created by BarackBao on 2018/2/3.
 * 设置Rx集合，方便解除订阅
 */
interface RxManager {
    fun dispose(disposable: Disposable)
    fun addDisposable(disposable: Disposable)
}