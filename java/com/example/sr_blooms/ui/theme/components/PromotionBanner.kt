package com.example.sr_blooms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sr_blooms.R
import com.example.sr_blooms.ui.theme.Shapes



//promotion banner in home page
@Composable
@Preview
fun PromoBanner(){
  Column(
      modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.background)
          .padding(8.dp)
  ) {

      Box(modifier = Modifier
          .fillMaxWidth()
          .background(
              brush = Brush.linearGradient(
                  colors = listOf(Color.Green, Color.Red),
              ),
              shape = Shapes.medium
          )

          .padding(14.dp) )
      {
          Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
          ) {
              Column(
                  modifier = Modifier.weight(1f)
              ) {
                  Text(
                      text = stringResource(R.string.today_s_deal),
                      style = MaterialTheme.typography.titleLarge,
                      fontWeight = FontWeight.Normal,
                      color = Color.White
                  )
                  Spacer(modifier = Modifier.height(8.dp))
                  Text(
                      text = stringResource(R.string.today_s_deal),
                      style = MaterialTheme.typography.titleMedium,
                      fontWeight = FontWeight.SemiBold,
                      color = Color.White

                  )
                  Spacer(modifier = Modifier.height(14.dp))
                  Text(
                      text = stringResource(R.string.condition_applied),
                      style = MaterialTheme.typography.titleSmall,
                      color = Color.White
                  )
              }
              // Image Section with Overlapping Pictures
              Box(
                  modifier = Modifier
                      .size(120.dp)
                      .padding(1.dp)

              ) {
                  Image(
                      painter = painterResource(id = R.drawable.plant),
                      contentDescription = "Flower Image",
                      modifier = Modifier
                          .size(140.dp)
                          .align(Alignment.CenterStart)
                          .offset(x = (-19).dp, y = (-50).dp)

                  )
                  Image(
                      painter = painterResource(id = R.drawable.orchid),
                      contentDescription = "Fries Image",
                      modifier = Modifier
                          .size(140.dp)
                          .align(Alignment.CenterEnd)
                          .offset(x = (15).dp, y = (12).dp)
                  )
              }
          }
      }
  }
}

