import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.nisaefendioglu.shoppingapp.R
import com.nisaefendioglu.shoppingapp.adapter.ProductAdapter
import com.nisaefendioglu.shoppingapp.databinding.FragmentHomeBinding
import com.nisaefendioglu.shoppingapp.model.Product
import com.nisaefendioglu.shoppingapp.view.ProductDetailFragment
import com.nisaefendioglu.shoppingapp.viewmodel.ProductViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private val viewModel: ProductViewModel by activityViewModels()
    private lateinit var loadingIndicator: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        loadingIndicator = requireView().findViewById(R.id.loadingIndicator)
        setInitialVisibility()
        observeProductList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareRecyclerView() {
        productAdapter = ProductAdapter()
        binding.recylerview.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
        }

        productAdapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onItemClick(product: Product) {
                navigateToProductDetail(product)
            }
        })
    }

    private fun navigateToProductDetail(product: Product) {
        val productDetailFragment = ProductDetailFragment.newInstance(product)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, productDetailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setInitialVisibility() {
        binding.recylerview.visibility = View.GONE
        binding.fragmentHome.visibility = View.GONE
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun observeProductList() {
        viewModel.getAllProducts().observe(viewLifecycleOwner, Observer { productList ->
            productAdapter.setProductList(productList)
            binding.loadingIndicator.visibility = View.GONE
            binding.recylerview.visibility = View.VISIBLE
            binding.fragmentHome.visibility = View.VISIBLE
        })
    }
}
