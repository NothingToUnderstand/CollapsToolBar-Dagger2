package com.example.molfartask.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater) -> T

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflate.invoke(inflater).also { _binding = it }.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.observeNotifierLiveData().observe(viewLifecycleOwner) {
            makeToast(it)
        }
    }

    @CallSuper
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    inline fun <reified VM : ViewModel> inject(): Lazy<VM> = lazy {
        ViewModelProvider(this, viewModelFactory)[VM::class.java]
    }

    protected fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}