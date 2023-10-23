package dev.panwar.a7minutesworkout.utils

import dev.panwar.a7minutesworkout.R
import dev.panwar.a7minutesworkout.model.ExerciseModel

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks =
            ExerciseModel(
                1,
                "Jumping Jacks",
                """
                    To perform a jumping jack, start by standing with your feet together and arms at your sides. Jump your feet apart while simultaneously raising your arms above your head. Then, quickly reverse the motion by jumping back to the starting position.
                """.trimIndent(),
                R.drawable.ic_jumping_jacks,
                false,
                false
            )
        exerciseList.add(jumpingJacks)

        val wallSit = ExerciseModel(
            2,
            "Wall Sit",
            """
                To perform a wall sit, start by standing about 2 feet away from a wall with your back against the wall. Then, slide your back down the wall until your hips and knees bend at a 90-degree angle. Hold this position for as long as you can.
            """.trimIndent(),
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        val pushUp = ExerciseModel(
            3,
            "Push Up",
            """
                To perform a pushup, start by lying face-down on the floor with your palms flat on the floor, about shoulder-width apart. Then, push yourself up off the floor until your arms are fully extended. Lower yourself back down until your chest is about an inch above the floor. Repeat.
            """.trimIndent(),
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val abdominalCrunch = ExerciseModel(
            4,
            "Abdominal Crunch",
            """
                To perform an abdominal crunch, start by lying on your back with your knees bent and feet flat on the floor. Then, place your hands behind your head and lift your shoulders off the floor while contracting your abs. Lower yourself back down to the starting position.
            """.trimIndent(),
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)

        val stepUpOnChair =
            ExerciseModel(
                5,
                "Step-Up onto Chair",
                """
                    To perform a step-up onto a chair, start by standing in front of a chair with your feet shoulder-width apart. Then, step onto the chair with one foot, pressing down while bringing your other foot up next to it. Step back down to the floor with the same foot, then repeat with the other foot.
                """.trimIndent(),
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            )
        exerciseList.add(stepUpOnChair)

        val squat = ExerciseModel(
            6,
            "Squat",
            """
                To perform a squat, start by standing with your feet shoulder-width apart. Then, bend your knees and lower your hips down and back. Keep your chest upright and your knees over your toes. Then, push through your heels to return to the starting position.
            """.trimIndent(),
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squat)

        val tricepDipOnChair =
            ExerciseModel(
                7,
                "Tricep Dip On Chair",
                """
                    To perform a tricep dip on a chair, start by sitting on the edge of the chair with your hands next to your hips. Then, slide your hips off the chair and bend your elbows to lower your hips down and back. Then, straighten your arms to return to the starting position.
                """.trimIndent(),
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false
            )
        exerciseList.add(tricepDipOnChair)

        val plank = ExerciseModel(
            8,
            "Plank",
            """
                To perform a plank, start by lying face-down on the floor with your legs extended and your elbows bent and directly under your shoulders. Then, push yourself up off the floor, keeping your forearms on the floor and your body in a straight line from head to feet. Hold this position for as long as you can.
            """.trimIndent(),
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val highKneesRunningInPlace =
            ExerciseModel(
                9,
                "High Knees Running In Place",
                """
                    To perform high knees running in place, start by standing with your feet hip-width apart. Then, run in place while bringing your knees up towards your chest as high as you can. Keep your core engaged and your back straight.
                """.trimIndent(),
                R.drawable.ic_high_knees_running_in_place,
                false,
                false
            )
        exerciseList.add(highKneesRunningInPlace)

        val lunges = ExerciseModel(
            10,
            "Lunges",
            """
                To perform a lunge, start by standing with your feet shoulder-width apart. Then, take a big step forward with one foot and bend your knee until your thigh is parallel to the floor. Then, push off your front foot to return to the starting position. Repeat with the other leg.
            """.trimIndent(),
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunges)

        val pushupAndRotation =
            ExerciseModel(
                11,
                "Push up and Rotation",
                """
                    To perform a pushup and rotation, start by performing a pushup. Then, at the top of the pushup, rotate your body to one side while raising one arm up towards the ceiling. Return to the starting position, then repeat on the other side.
                """.trimIndent(),
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            )
        exerciseList.add(pushupAndRotation)

        val sidePlank = ExerciseModel(
            12,
            "Side Plank",
            """
                To perform a side plank, start by lying on your side with your legs extended and your feet and hips resting on the floor. Then, prop yourself up on your forearm so your body forms a diagonal line. Hold this position for as long as you can, then repeat on the other side.
            """.trimIndent(),
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)

        return exerciseList
    }
}