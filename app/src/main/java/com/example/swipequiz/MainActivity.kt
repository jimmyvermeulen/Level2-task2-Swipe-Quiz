package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews(){
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter

        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        for (i in Question.QUESTION_STATEMENTS.indices) {
            questions.add(Question(Question.QUESTION_STATEMENTS[i], Question.QUESTION_ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val answer = questions[position].answer
                if(direction == ItemTouchHelper.LEFT){
                    if(answer == false){
                        Snackbar.make(rvQuestions, getResources().getString(R.string.correct_answer) + answer.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    else{
                        Snackbar.make(rvQuestions, getResources().getString(R.string.incorrect_answer) + answer.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }
                else{
                    if(answer == true){
                        Snackbar.make(rvQuestions, getResources().getString(R.string.correct_answer) + answer.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    else{
                        Snackbar.make(rvQuestions, getResources().getString(R.string.incorrect_answer) + answer.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                }

                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)

            }
        }
        return ItemTouchHelper(callback)
    }

}
