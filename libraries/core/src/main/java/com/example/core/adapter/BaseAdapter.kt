package com.example.core.adapter

import androidx.annotation.LayoutRes

abstract class BaseAdapter {

    @LayoutRes
    protected abstract fun layoutId(): Int
}