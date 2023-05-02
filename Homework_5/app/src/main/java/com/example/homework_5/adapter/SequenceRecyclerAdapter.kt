import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_5.R
import com.example.homework_5.model.Forecast

class SequenceRecyclerAdapter(private var sequenceWeatherData: List<Forecast>) :
    RecyclerView.Adapter<SequenceRecyclerAdapter.SequenceViewHolder>() {

    class SequenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.time_item_hour)
        val temperature: TextView = view.findViewById(R.id.time_item_temperature)
        val weatherIcon: ImageView = view.findViewById(R.id.time_item_weather_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.time_item_view, parent, false)
        return SequenceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SequenceViewHolder, position: Int) {
        val sequenceWeather = sequenceWeatherData[position]
        //holder.time.text = sequenceWeather.time.toString()
        //holder.temperature.text = "${sequenceWeather.temp_c}°"
        //holder.weatherIcon.setImageIcon(sequenceWeather.condition.icon)
    }

    override fun getItemCount(): Int {
        return sequenceWeatherData.size
    }

    fun updateData(newSequenceWeatherData: List<Forecast>) {
        //sequenceWeatherData = newSequenceWeatherData.first().forecastDay.first().hour
        notifyDataSetChanged()
    }
}
