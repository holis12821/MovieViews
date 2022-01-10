package com.example.movieviews.utils

import com.example.movieviews.data.models.MovieEntity

/**
 * Singleton object to get and produce data dummy movie*/
object DataMovieDummy {

    fun getMovies(): List<MovieEntity> {
        return mutableListOf(
            MovieEntity(
                id = 242021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sR3iV0Jt080jgvPBtJhs3Tta1y9.jpg",
                title = "Resident Evil: Welcome to Raccoon City",
                tagLine = "Witness the beginning of evil.",
                releaseDate = "Nov 24, 2021",
                rating = 7.8,
                overview = """Once the booming home of pharmaceutical giant Umbrella Corporation, 
                    |Raccoon City is now a dying Midwestern town. 
                    |The company’s exodus left the city a wasteland…with great evil brewing below the surface. When that evil is unleashed, 
                    |the townspeople are forever…changed…and a small group of survivors must work together to uncover the truth behind Umbrella and make it through the night.""".trimMargin(),
                 genres = listOf(
                    "Horror",
                    "Action",
                    "Science Fiction"
                ),
                duration = "1h 47m",
                freeWatch = false
            ),
            MovieEntity(
                id = 152021,
                posterUrl = "https://www.themoviedb.org/t/p/w440_and_h660_face/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                title = "Spider-Man: No Way Home",
                tagLine = "The Multiverse unleashed.",
                releaseDate = "Dec 15, 2021",
                rating = 8.6,
                overview = """Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. 
                    |When he asks for help from Doctor Strange the stakes become even more dangerous, 
                    |forcing him to discover what it truly means to be Spider-Man.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 28m",
                freeWatch = false
            ),

            MovieEntity(
                id = 302021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                title = "Venom: Let There Be Carnage",
                tagLine = "",
                releaseDate = "Sep 30, 2021",
                rating = 7.2,
                overview = """After finding a host body in investigative reporter Eddie Brock,
                    | the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.""".trimMargin(),
                genres = listOf(
                    "Science Fiction",
                    " Action",
                    "Adventure"
                ),
                duration = "1h 37m",
                freeWatch = false
            ),

            MovieEntity(
                id = 112021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fQZxAA6PPWa2Lk9NgcO3m6QDlXQ.jpg",
                title = "Ghostbusters: Afterlife",
                tagLine = "Discover the past. Protect the future.",
                releaseDate = "Nov 11, 2021",
                rating = 7.2,
                overview = """When a single mom and her two kids arrive in a small town, 
                    |they begin to discover their connection to the original
                    | Ghostbusters and the secret legacy their grandfather left behind.""".trimMargin(),
                genres = listOf(
                    "Comedy",
                    "Fantasy",
                    "Adventure"
                ),
                duration = "2h 4m",
                freeWatch = false
            ),

            MovieEntity(
                id = 42021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lAXONuqg41NwUMuzMiFvicDET9Y.jpg",
                title = "Red Notice",
                tagLine = "Pro and cons.",
                releaseDate = "Nov 04, 2021",
                rating = 6.8,
                overview = """An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. 
                    |But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Comedy",
                    "Crime",
                    "Thriller"
                ),
                duration = "1h 57m",
                freeWatch = true
            ),

            MovieEntity(
                id = 12021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
                title = "Shang-Chi and the Legend of the Ten Rings",
                tagLine = "You can't outrun your destiny.",
                releaseDate = "Sep 01, 2021",
                rating = 7.8,
                overview = """Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy",
                ),
                duration = "2h 12m",
                freeWatch = false
            ),

            MovieEntity(
                id = 32021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6AdXwFTRTAzggD2QUTt5B7JFGKL.jpg",
                title = "Eternals",
                tagLine = "In the beginning...",
                releaseDate = "Nov 03, 2021",
                rating = 7.1,
                overview = """The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. 
                    |When an unexpected tragedy forces them out of the shadows,
                    | they are forced to reunite against mankind’s most ancient enemy, the Deviants.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy",
                    "Science Fiction"
                ),
                duration = "2h 37m",
                freeWatch = false
            ),

            MovieEntity(
                id = 162021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gZlZLxJMfnSeS60abFZMh1IvODQ.jpg",
                title = "The Matrix Resurrections",
                tagLine = "Return to the source.",
                releaseDate = "Dec 16, 2021",
                rating = 7.0,
                overview = """Plagued by strange memories, 
                    |Neo's life takes an unexpected turn when he finds himself back inside the Matrix.""".trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Action",
                    "Science Fiction"
                ),
                duration = "2h 28m",
                freeWatch = true
            ),

            MovieEntity(
                id = 12021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/34Xss3gwKdwvtomCDkeC2lW4PVB.jpg",
                title = "Harry Potter 20th Anniversary: Return to Hogwarts",
                tagLine = "Welcome back to where the magic began.",
                releaseDate = "Jan 01, 2022",
                rating = 8.5,
                overview = """An enchanting making-of story told through all-new in-depth interviews and cast conversations, 
                    |inviting fans on a magical first-person journey through one of the most beloved film franchises of all time.""".trimMargin(),
                genres = listOf(
                    "Documentary"
                ),
                duration = "1h 43m",
                freeWatch = false
            ),

            MovieEntity(
                id = 92021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dU4HfnTEJDf9KvxGS9hgO7BVeju.jpg",
                title = "After We Fell",
                tagLine = "",
                releaseDate = "Sep 01, 2021",
                rating = 7.2,
                overview = """Just as Tessa's life begins to become unglued, 
                    |nothing is what she thought it would be. Not her friends nor her family. 
                    |The only person that she should be able to rely on is Hardin, who is furious when he discovers the massive secret that she's been keeping.
                    | Before Tessa makes the biggest decision of her life, everything changes because of revelations about her family.""".trimMargin(),
                genres = listOf(
                    "Romance",
                    "Drama"
                ),
                duration = "1h 39m",
                freeWatch = true
            ),

            MovieEntity(
                id = 22020,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                title = "After We Collided",
                tagLine = "Can love overcome the past?.",
                releaseDate = "Sep 2, 2020",
                rating = 7.3,
                overview = """Tessa finds herself struggling with her complicated relationship with Hardin; 
                    |she faces a dilemma that could change their lives forever.""".trimMargin(),
                genres = listOf(
                    "Romance",
                    "Drama"
                ),
                duration = "1h 45m",
                freeWatch = true
            ),

            MovieEntity(
                id = 232012,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fSbqPbqXa7ePo8bcnZYN9AHv6zA.jpg",
                title = "The Amazing Spider-Man",
                tagLine = "The untold story begins.",
                releaseDate = "Jun 23, 2012",
                rating = 6.6,
                overview = """Peter Parker is an outcast high schooler abandoned by his parents as a boy, 
                    |leaving him to be raised by his Uncle Ben and Aunt May. Like most teenagers, 
                    |Peter is trying to figure out who he is and how he got to be the person he is today. 
                    |As Peter discovers a mysterious briefcase that belonged to his father, he begins a quest to understand his parents' 
                    |disappearance – leading him directly to Oscorp and the lab of Dr.
                    | Curt Connors, his father's former partner. As Spider-Man is set on a collision course with Connors' alter ego, 
                    | The Lizard, Peter will make life-altering choices to use his powers and shape his destiny to become a hero.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy"
                ),
                duration = "2h 16m",
                freeWatch = false
            ),

            MovieEntity(
                id = 52017,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                title = "Spider-Man: Homecoming",
                tagLine = "Homework can wait. The city can't.",
                releaseDate = "Jul 05, 2017",
                rating = 7.4,
                overview = """Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark,
                    | tries to balance his life as an ordinary high school student in Queens, New York City, 
                    | with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction",
                    "Drama"
                ),
                duration = "2h 13m",
                freeWatch = true
            ),

            MovieEntity(
                id = 282019,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg",
                title = "Spider-Man: Far From Home",
                tagLine = "It’s time to step up.",
                releaseDate = "Jun 28, 2019",
                rating = 7.5,
                overview = """Peter Parker and his friends go on a summer trip to Europe. However, 
                    |they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the
                    | mystery of creatures that cause natural disasters and destruction throughout the continent.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 9m",
                freeWatch = true
            ),

            MovieEntity(
                id = 72021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg",
                title = "Black Widow",
                tagLine = "Her world. Her secrets. Her legacy.",
                releaseDate = "Jul 07, 2021",
                rating = 7.6,
                overview = """Natasha Romanoff, also known as Black Widow, confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.
                    | Pursued by a force that will stop at nothing to bring her down, 
                    | Natasha must deal with her history as a spy and the broken relationships left in her wake long before she became an Avenger.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 14m",
                freeWatch = false
            ),

            MovieEntity(
                id = 72021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                title = "Mortal Kombat",
                tagLine = "Get over here.",
                releaseDate = "Apr 07, 2021",
                rating = 7.3,
                overview = """Washed-up MMA fighter Cole Young, unaware of his heritage, 
                    |and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions
                    | as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.""".trimMargin(),
                genres = listOf(
                    "Action",
                    "Fantasy",
                    "Adventure"
                ),
                duration = "1h 50m",
                freeWatch = false
            ),

            MovieEntity(
                id = 42021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kreTuJBkUjVWePRfhHZuYfhNE1T.jpg",
                title = "Five Feet Apart",
                tagLine = "When Life Keeps You Apart, Fight For Every Inch.",
                releaseDate = "March 14, 2019",
                rating = 8.3,
                overview = """Seventeen-year-old Stella spends most of her time in the hospital as a cystic fibrosis patient. 
                    |Her life is full of routines, boundaries and self-control — all of which get put to the test when she meets Will,
                    | an impossibly charming teen who has the same illness. There's an instant flirtation, 
                    | though restrictions dictate that they must maintain a safe distance between them. 
                    | As their connection intensifies, so does the temptation to throw the rules out the window and embrace that attraction.""".trimMargin(),
                genres = listOf(
                    "Romance,",
                    "Drama"
                ),
                duration = "1h 56m",
                freeWatch = true
            ),

            MovieEntity(
                id = 42021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                title = "Hawkeye",
                tagLine = "This holiday season, the best gifts come with a bow.",
                releaseDate = "Nov 24, 2021",
                rating = 8.4,
                overview = """Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. 
                    |Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. 
                    |The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.""".trimMargin(),
                genres = listOf(
                    "Action & Adventure",
                    "Drama"
                ),
                duration = "1h 57m",
                freeWatch = false
            ),

            MovieEntity(
                id = 242019,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                title = "Avengers: Endgame",
                tagLine = "Part of the journey is the end.",
                releaseDate = "April 24, 2019",
                rating = 8.3,
                overview = """After the devastating events of Avengers: Infinity War, 
                    |the universe is in ruins due to the efforts of the Mad Titan, 
                    |Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' 
                    |actions and restore order to the universe once and for all, 
                    |no matter what consequences may be in store.""".trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Science Fiction",
                    "Crime",
                    "Thriller"
                ),
                duration = "3h 1m",
                freeWatch = true
            ),

            MovieEntity(
                id = 42021,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lAXONuqg41NwUMuzMiFvicDET9Y.jpg",
                title = "No Time to Die",
                tagLine = "The mission that changes everything begins…",
                releaseDate = "Sep 29, 2021",
                rating = 7.5,
                overview = """Bond has left active service and is enjoying a tranquil life in Jamaica. 
                    |His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. 
                    |The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, 
                    |leading Bond onto the trail of a mysterious villain armed with dangerous new technology.""".trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Action",
                    "Thriller"
                ),
                duration = "2h 43m",
                freeWatch = true
            )
        )
    }

}