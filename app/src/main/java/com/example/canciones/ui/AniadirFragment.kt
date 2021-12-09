package com.example.canciones.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.canciones.R
import com.example.canciones.databinding.FragmentAniadirBinding
import com.example.canciones.viewmodel.SongViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class   AniadirFragment : Fragment() {

    private var _binding: FragmentAniadirBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val model : SongViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAniadirBinding.inflate(inflater, container, false)
        binding.aniadirCancion.setOnClickListener{
            addSong()
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun addSong(){
        val title = binding.entrada1.text.toString()
        val author = binding.entrada2.text.toString()
        val album = binding.entrada3.text.toString()
        val year = binding.entrada4.text.toString()
        model.addNewSong(title,author,album,year)
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}