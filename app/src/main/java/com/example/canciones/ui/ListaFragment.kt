package com.example.canciones.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.canciones.R
import com.example.canciones.databinding.FragmentListaBinding
import com.example.canciones.db.Song
import com.example.canciones.viewmodel.SongViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListaFragment : Fragment() {

    private var _binding: FragmentListaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model : SongViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId == R.id.delete){
            model.deleteAllSongs()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val songsObserver = Observer<List<Song>>{
            updateDisplayText(model.mAllSongs.value)
            binding.listaCanciones.text = model.displayText
        }
        model.mAllSongs.observe(viewLifecycleOwner, songsObserver)
        binding.aniadirButton.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.borrarButton.setOnClickListener{
            model.deleteAllSongs()
        }
    }

    private fun updateDisplayText(songs : List<Song>?){
        if (songs!=null){
            for (song in songs){
                model.displayText+= "${song.title}\n${song.author} - ${song.album}\n${song.year}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}