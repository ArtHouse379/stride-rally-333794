package teeupgolfleisure.athletics.striderally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import teeupgolfleisure.athletics.striderally.ui.composable.approot.AppRoot
import teeupgolfleisure.athletics.striderally.ui.theme.SportTrackerAppDonorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportTrackerAppDonorTheme {
                AppRoot()
            }
        }
    }
}
