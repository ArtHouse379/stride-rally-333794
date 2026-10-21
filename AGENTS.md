# Stride Rally

This project was generated from a donor template (`/home/codex-agent/constructor-white-projects/assets/sport-tracker-template`) by
TemplateForge. Application type: **SportTracker**.

- Package: `teeupgolfleisure.athletics.striderally`
- Project name: `Stride Rally`

## Generated content structure

- `../data/model/WorkoutModel.kt` (the WorkoutType enum) — 3 to 4
  workout categories invented for the topic, each with a matching icon,
  replacing the current Cardio/Strength/Flexibility placeholders.
- `../data/repository/WorkoutRepository.kt` — catalog of workouts
  (6-8 total), each with 4-6 exercises, covering all of the categories
  above and all three Difficulty values (Beginner, Intermediate,
  Advanced).
  - Each `ExerciseModel` is either rep-based (`sets` and `repetitions`
    both non-null, `duration` null) or duration-based (`duration`
    non-null, `sets` and `repetitions` null). Never leave all three of
    `duration`, `sets`, `repetitions` null, and never set `duration` to
    `0` — the UI starts a countdown from it and an exercise with no
    time or reps completes instantly.
  - `duration` and `restDuration` are `Long` **milliseconds**, not
    seconds. Write them as a whole-second count times 1000, using the
    `N_NNNL` underscore literal, e.g. `30_000L` for 30 seconds,
    `120_000L` for 2 minutes — never a bare `120` or `30`.
- `../data/repository/TipRepository.kt` — catalog of tips (5-6 total)
  about sport or health, matching the app's topic.
- String resources follow the existing naming convention
  (`workout_type_<category_name>_title` for the new categories). The
  difficulty_* and intensity_* resources are fixed enum labels the
  app's filtering and statistics screens rely on and must keep their
  original meaning regardless of topic.

## Working in this repo

- Keep changes scoped to what the current task asks for — this is a
  freshly generated project, not a hand-maintained one, so don't
  "clean up" unrelated files.
- Preserve the existing package structure and naming conventions found
  in the generated code instead of introducing new patterns.
- Do not modify build/gradle configuration, project name, package name,
  or app id — those were set intentionally during generation.
- Do not run Gradle builds, dependency downloads, Android lint, or emulator
  tests. The project is published as-is after your work. Validate only the
  modified data and resource files with lightweight local checks.
- Do not change business logic or UI code — limit changes strictly to
  the placeholder data described above (workout categories, workouts,
  exercises, tips, string resources).
- When filling in placeholder data (strings, repository objects, etc.),
  match the style and format already used elsewhere in the file.
- All generated text (workout names, exercise names, tip titles and
  messages, string resources) must be written in English, even if the
  task's input (e.g. the topic) is given in another language.
