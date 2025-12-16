

package com.example.appconfiguration
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appconfiguration.databinding.FragmentStreamersBinding


class StreamersFragment : Fragment() {

    private var _binding: FragmentStreamersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStreamersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aquí puedes agregar tu lógica para mostrar el CRUD de streamers
        // Por ahora solo muestra el texto del fragmento
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}