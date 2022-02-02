package com.example.movieviews.external.dumydata

import com.example.movieviews.data.local.MovieEntity

/**
 * Singleton object to get and produce data dummy movie*/
object DataMovieDummy {

    fun getMovies(): List<MovieEntity> {
        return mutableListOf(
            MovieEntity(
                id = 1,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sR3iV0Jt080jgvPBtJhs3Tta1y9.jpg",
                title = "Resident Evil: Welcome to Raccoon City",
                tagLine = "Witness the beginning of evil.",
                releaseDate = "Nov 24, 2021",
                rating = 7.8,
                overview = """Once the booming home of pharmaceutical giant Umbrella Corporation, 
                    |Raccoon City is now a dying Midwestern town. 
                    |The company’s exodus left the city a wasteland…with great evil brewing below the surface. 
                    |When that evil is unleashed, the townspeople are forever…changed…and a small group of survivors must work together
                    | to uncover the truth behind Umbrella and make it through the night."""
                    .trimMargin(),
                 genres = listOf(
                    "Horror",
                    "Action",
                    "Science Fiction"
                ),
                duration = "1h 47m",
                isTrending = true,
                isPopular = true,
                certification = "R",
                cast = DataCastDummy.getCastResidentEvil()
            ),
            MovieEntity(
                id = 2,
                posterUrl = "https://www.themoviedb.org/t/p/w440_and_h660_face/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                title = "Spider-Man: No Way Home",
                tagLine = "The Multiverse unleashed.",
                releaseDate = "Dec 15, 2021",
                rating = 8.6,
                overview = """Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. 
                    |When he asks for help from Doctor Strange the stakes become even more dangerous, 
                    |forcing him to discover what it truly means to be Spider-Man."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 28m",
                isTrending = true,
                isPopular = true,
                cast = DataCastDummy.getCastSpiderManNoWayHome()
            ),

            MovieEntity(
                id = 3,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                title = "Venom: Let There Be Carnage",
                tagLine = "",
                releaseDate = "Sep 30, 2021",
                rating = 7.2,
                overview = """After finding a host body in investigative reporter Eddie Brock,
                    | the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady."""
                    .trimMargin(),
                genres = listOf(
                    "Science Fiction",
                    " Action",
                    "Adventure"
                ),
                duration = "1h 37m",
                isTrending = true,
                isPopular = true,
                cast = DataCastDummy.getCastVenomLetThereBeCarnage()
            ),

            MovieEntity(
                id = 4,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fQZxAA6PPWa2Lk9NgcO3m6QDlXQ.jpg",
                title = "Ghostbusters: Afterlife",
                tagLine = "Discover the past. Protect the future.",
                releaseDate = "Nov 11, 2021",
                rating = 7.2,
                overview = """When a single mom and her two kids arrive in a small town, 
                    |they begin to discover their connection to the original
                    | Ghostbusters and the secret legacy their grandfather left behind."""
                    .trimMargin(),
                genres = listOf(
                    "Comedy",
                    "Fantasy",
                    "Adventure"
                ),
                duration = "2h 4m",
                isTrending = true,
                isPopular = true
            ),

            MovieEntity(
                id = 5,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lAXONuqg41NwUMuzMiFvicDET9Y.jpg",
                title = "Red Notice",
                tagLine = "Pro and cons.",
                releaseDate = "Nov 04, 2021",
                rating = 6.8,
                overview = """An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. 
                    |But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Comedy",
                    "Crime",
                    "Thriller"
                ),
                duration = "1h 57m",
                isFreeWatch = true,
                isTrending = true
            ),

            MovieEntity(
                id = 6,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1BIoJGKbXjdFDAqUEiA2VHqkK1Z.jpg",
                title = "Shang-Chi and the Legend of the Ten Rings",
                tagLine = "You can't outrun your destiny.",
                releaseDate = "Sep 01, 2021",
                rating = 7.8,
                overview = """Shang-Chi must confront the past he thought he 
                    |left behind when he is drawn into the web of the mysterious Ten Rings organization."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy",
                ),
                duration = "2h 12m",
                isTrending = true,
                isPopular = true
            ),

            MovieEntity(
                id = 7,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5L7bclqxXtsqsitP83KpkZbgTQ3.jpg",
                title = "Eternals",
                tagLine = "In the beginning...",
                releaseDate = "Nov 03, 2021",
                rating = 7.2,
                overview = """The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. 
                    |When an unexpected tragedy forces them out of the shadows,
                    | they are forced to reunite against mankind’s most ancient enemy, the Deviants."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy",
                    "Science Fiction"
                ),
                duration = "2h 37m",
                isTrending = true,
                isPopular = true
            ),

            MovieEntity(
                id = 8,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gZlZLxJMfnSeS60abFZMh1IvODQ.jpg",
                title = "The Matrix Resurrections",
                tagLine = "Return to the source.",
                releaseDate = "Dec 16, 2021",
                rating = 7.0,
                overview = """Plagued by strange memories, 
                    |Neo's life takes an unexpected turn when he finds himself back inside the Matrix."""
                    .trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Action",
                    "Science Fiction"
                ),
                duration = "2h 28m",
                isFreeWatch = true,
                isTrending = true
            ),

            MovieEntity(
                id = 9,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/34Xss3gwKdwvtomCDkeC2lW4PVB.jpg",
                title = "Harry Potter 20th Anniversary: Return to Hogwarts",
                tagLine = "Welcome back to where the magic began.",
                releaseDate = "Jan 01, 2022",
                rating = 8.5,
                overview = """An enchanting making-of story told through all-new in-depth interviews and cast conversations, 
                    |inviting fans on a magical first-person journey through one of the most beloved film franchises of all time."""
                    .trimMargin(),
                genres = listOf(
                    "Documentary"
                ),
                duration = "1h 43m",
                isTrending = true,
                isPopular = true
            ),

            MovieEntity(
                id = 10,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dU4HfnTEJDf9KvxGS9hgO7BVeju.jpg",
                title = "After We Fell",
                tagLine = "",
                releaseDate = "Sep 01, 2021",
                rating = 7.2,
                overview = """Just as Tessa's life begins to become unglued, 
                    |nothing is what she thought it would be. Not her friends nor her family. 
                    |The only person that she should be able to rely on is Hardin, who is furious when he discovers the massive secret that she's been keeping.
                    | Before Tessa makes the biggest decision of her life, everything changes because of revelations about her family."""
                    .trimMargin(),
                genres = listOf(
                    "Romance",
                    "Drama"
                ),
                duration = "1h 39m",
                isFreeWatch = true,
                certification = "R"
            ),

            MovieEntity(
                id = 11,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                title = "After We Collided",
                tagLine = "Can love overcome the past?.",
                releaseDate = "Sep 2, 2020",
                rating = 7.3,
                overview = """Tessa finds herself struggling with her complicated relationship with Hardin; 
                    |she faces a dilemma that could change their lives forever."""
                    .trimMargin(),
                genres = listOf(
                    "Romance",
                    "Drama"
                ),
                duration = "1h 45m",
                isFreeWatch = true,
                certification = "R"
            ),

            MovieEntity(
                id = 12,
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
                    | The Lizard, Peter will make life-altering choices to use his powers and shape his destiny to become a hero."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Fantasy"
                ),
                duration = "2h 16m",
            ),

            MovieEntity(
                id = 13,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                title = "Spider-Man: Homecoming",
                tagLine = "Homework can wait. The city can't.",
                releaseDate = "Jul 05, 2017",
                rating = 7.4,
                overview = """Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark,
                    | tries to balance his life as an ordinary high school student in Queens, New York City, 
                    | with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction",
                    "Drama"
                ),
                duration = "2h 13m",
                isFreeWatch = true
            ),

            MovieEntity(
                id = 14,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg",
                title = "Spider-Man: Far From Home",
                tagLine = "It’s time to step up.",
                releaseDate = "Jun 28, 2019",
                rating = 7.5,
                overview = """Peter Parker and his friends go on a summer trip to Europe. However, 
                    |they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the
                    | mystery of creatures that cause natural disasters and destruction throughout the continent."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 9m",
                isFreeWatch = true
            ),

            MovieEntity(
                id = 15,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg",
                title = "Black Widow",
                tagLine = "Her world. Her secrets. Her legacy.",
                releaseDate = "Jul 07, 2021",
                rating = 7.6,
                overview = """Natasha Romanoff, also known as Black Widow, confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.
                    | Pursued by a force that will stop at nothing to bring her down, 
                    | Natasha must deal with her history as a spy and the broken relationships left in her wake long before she became an Avenger."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Adventure",
                    "Science Fiction"
                ),
                duration = "2h 14m",
                isPopular = true
            ),

            MovieEntity(
                id = 16,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                title = "Mortal Kombat",
                tagLine = "Get over here.",
                releaseDate = "Apr 07, 2021",
                rating = 7.3,
                overview = """Washed-up MMA fighter Cole Young, unaware of his heritage, 
                    |and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions
                    | as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe."""
                    .trimMargin(),
                genres = listOf(
                    "Action",
                    "Fantasy",
                    "Adventure"
                ),
                duration = "1h 50m",
                isPopular = true,
                certification = "R"
            ),

            MovieEntity(
                id = 17,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kreTuJBkUjVWePRfhHZuYfhNE1T.jpg",
                title = "Five Feet Apart",
                tagLine = "When Life Keeps You Apart, Fight For Every Inch.",
                releaseDate = "March 14, 2019",
                rating = 8.3,
                overview = """Seventeen-year-old Stella spends most of her time in the hospital as a cystic fibrosis patient. 
                    |Her life is full of routines, boundaries and self-control — all of which get put to the test when she meets Will,
                    | an impossibly charming teen who has the same illness. There's an instant flirtation, 
                    | though restrictions dictate that they must maintain a safe distance between them. 
                    | As their connection intensifies, so does the temptation to throw the rules out the window and embrace that attraction."""
                    .trimMargin(),
                genres = listOf(
                    "Romance,",
                    "Drama"
                ),
                duration = "1h 56m",
                isFreeWatch = true
            ),

            MovieEntity(
                id = 18,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                title = "Hawkeye",
                tagLine = "This holiday season, the best gifts come with a bow.",
                releaseDate = "Nov 24, 2021",
                rating = 8.4,
                overview = """Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. 
                    |Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. 
                    |The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit."""
                    .trimMargin(),
                genres = listOf(
                    "Action & Adventure",
                    "Drama"
                ),
                duration = "1h 57m",
                isTrending = true,
                isPopular = true,
                certification = "TV-14"
            ),

            MovieEntity(
                id = 19,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                title = "Avengers: Endgame",
                tagLine = "Part of the journey is the end.",
                releaseDate = "April 24, 2019",
                rating = 8.3,
                overview = """After the devastating events of Avengers: Infinity War, 
                    |the universe is in ruins due to the efforts of the Mad Titan, 
                    |Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' 
                    |actions and restore order to the universe once and for all, 
                    |no matter what consequences may be in store."""
                    .trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Science Fiction",
                    "Crime",
                    "Thriller"
                ),
                duration = "3h 1m",
                isFreeWatch = true,
                isPopular = true,
                certification = "13+"
            ),

            MovieEntity(
                id = 20,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg",
                title = "No Time to Die",
                tagLine = "The mission that changes everything begins…",
                releaseDate = "Sep 29, 2021",
                rating = 7.5,
                overview = """Bond has left active service and is enjoying a tranquil life in Jamaica. 
                    |His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. 
                    |The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, 
                    |leading Bond onto the trail of a mysterious villain armed with dangerous new technology."""
                    .trimMargin(),
                genres = listOf(
                    "Adventure",
                    "Action",
                    "Thriller"
                ),
                duration = "2h 43m",
                isFreeWatch = true,
                isTrending = true
            ),

            MovieEntity(
                id = 21,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg",
                title = "Sing 2",
                tagLine = "Where will your dreams take you?",
                releaseDate = "Jan 28, 2022",
                rating = 7.5,
                overview = """Buster and his new cast now have their sights set on debuting a new show at the Crystal Tower Theater in glamorous Redshore City.
                    | But with no connections, he and his singers must sneak into the Crystal Entertainment offices, run by the ruthless wolf mogul Jimmy Crystal,
                    | where the gang pitches the ridiculous idea of casting the lion rock legend Clay Calloway in their show. 
                    | Buster must embark on a quest to find the now-isolated Clay and persuade him to return to the stage."""
                    .trimMargin(),
                genres = listOf(
                    "Animation",
                    "Comedy",
                    "Family",
                    " Music"
                ),
                duration = "1h 50m",
                isUpComing = true,
                certification = "PG"
            ),
            MovieEntity(
                id = 22,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qvASAp0ZKkza023gjK1Tf2iiEos.jpg",
                title = "Scream",
                tagLine = "It's always someone you know.",
                releaseDate = "Jan 14, 2022",
                rating = 8.4,
                overview = """Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, 
                    |a new killer has donned the Ghostface mask and begins targeting 
                    |a group of teenagers to resurrect secrets from the town’s deadly past."""
                    .trimMargin(),
                genres = listOf(
                    "Thriller",
                    "Horror",
                    " Mystery"
                ),
                duration = "1h 54m",
                isUpComing = true,
                certification = "R"
            ),

            MovieEntity(
                id = 23,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9FC1wqVpaWvaIcAbu6bIYgFDu1z.jpg",
                title = "Aline",
                tagLine = "A fiction freely inspired by the life of Celine Dion.",
                releaseDate = "Jan 21, 2022",
                rating = 7.1,
                overview = """For Aline Dieu, nothing in the world matters more than music, 
                    |family and love. Her powerful and emotional voice captivates everyone who hears it, 
                    |including successful manager Guy-Claude Kamar, who resolves to do everything in his power to make her a star. 
                    |As Aline climbs from local phenomenon to bestselling recording artist to international superstar, 
                    |she embarks on the two great romances of her life: one with the decades-older Guy- 
                    |Claude and the other with her adoring audiences."""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Comedy",
                    "Music"
                ),
                duration = "2h 8m",
                isUpComing = true,
                certification = "U"
            ),

            MovieEntity(
                id = 24,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fQS2ZEmq2wLjmKLUhcBVdBlzg72.jpg",
                title = "Orphan: First Kill",
                tagLine = "",
                releaseDate = "Jan 28, 2022",
                rating = 0.0,
                overview = """Leena Klammer orchestrates a brilliant escape from an Estonian psychiatric facility
                    | and travels to America by impersonating the missing daughter of a wealthy family. 
                    |But Leena’s new life as “Esther” comes with an unexpected wrinkle and pits her against
                    | a mother who will protect her family at any cost."""
                    .trimMargin(),
                genres = listOf(
                    "Crime",
                    "Horror",
                    "Thriller"
                ),
                duration = "",
                isUpComing = true,
                certification = ""
            ),

            MovieEntity(
                id = 25,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yA6Pg5QWHPC3Np5JiI0zQnSQD48.jpg",
                title = "Dear Nathan: Thank You Salma",
                tagLine = "",
                releaseDate = "Jan 13, 2022",
                rating = 0.0,
                overview = """Nathan and Salma enter the world of social activism. 
                    |Salma chose to express himself digitally while Nathan chose to take to the streets. 
                    |This difference sparked a big fight when Nathan was involved in a big riot at a demonstration. 
                    |Their romance ended. Nathan tries to fight for Salma to continue his relationship. 
                    |Unfortunately, Nathan's time is torn apart after Rebecca asks him to help Zanna, who is sexually assaulted. 
                    |Nathan is in a difficult position, because he has to protect Zanna's privacy and keep this matter a secret from everyone including Salma.
                    | The problem is compounded when Salma is close to Afkar, a musician who has long idolized Salma. 
                    | Nathan never stopped fighting for justice for Zanna, but at the same time he was also not willing to lose Salma."""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Romance"
                ),
                duration = "1h 52m",
                isUpComing = true,
                certification = "13+"
            ),

            MovieEntity(
                id = 26,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dfeG7SUbHOOCmmOoABJ16OghZPj.jpg",
                title = "Merindu Cahaya De Amstel",
                tagLine = "",
                releaseDate = "Jan 20, 2022",
                rating = 0.0,
                overview = """Nico and Khadija's relationship that has a mutual heart but is hindered by 
                    |the bitter story of the past and the difference in beliefs between the two"""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Romance",
                ),
                duration = "1h 47m",
                isUpComing = true,
                certification = ""
            ),

            MovieEntity(
                id = 27,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/klEycDI8ciKP9Kb6T1ed8FzXyDw.jpg",
                title = "Ben & Jody",
                tagLine = "",
                releaseDate = "Jan 27, 2022",
                rating = 0.0,
                overview = """Since deciding to leave Filosofi Kopi, Ben has lived in his hometown and has been actively defending farmer groups whose lands have been taken over by the Company. 
                    |At the same time, Jody is preparing a new concept for Filosofi Kopi which will be launched soon. Ahead of the launch event, Ben who was scheduled to attend suddenly disappears.
                    | Jody sets out to find Ben. In order to save Ben, they face life and death adventures against gangs of illegal loggers led by Tubir, 
                    |until their encounter with a traditional village group that changes the map of their resistance."""
                    .trimMargin(),
                genres = listOf(
                    "New Adventure",
                    "New Stake",
                    "New Journey"
                ),
                duration = "1h 54m",
                isUpComing = true,
                certification = ""
            ),

            MovieEntity(
                id = 28,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/prbZxJxGcy07y60eq8lCGMciTYz.jpg",
                title = "The Hating Game",
                tagLine = "He's the one she loves to hate.",
                releaseDate = "Dec 09, 2021",
                rating = 7.6,
                overview = """Resolving to achieve professional success without compromising her ethics, 
                    |Lucy embarks on a ruthless game of one-upmanship against cold and efficient nemesis Joshua,
                    | a rivalry that is complicated by her growing attraction to him."""
                    .trimMargin(),
                genres = listOf(
                    "Comedy",
                    "Romance"
                ),
                duration = "1h 54m",
                isUpComing = true,
                certification = "R"
            ),

            MovieEntity(
                id = 29,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg",
                title = "The Wheel of Time",
                tagLine = "",
                releaseDate = "Nov 18, 2021",
                rating = 8.0,
                overview = """Follow Moiraine, a member of the shadowy and influential all-female organization called the “Aes Sedai” 
                    |as she embarks on a dangerous, world-spanning journey with five young men and women. 
                    |Moiraine believes one of them might be the reincarnation of an incredibly powerful individual, 
                    |whom prophecies say will either save humanity or destroy it."""
                    .trimMargin(),
                genres = listOf(
                    "Sci-Fi & Fantasy",
                    "Drama"
                ),
                episode = 8,
                certification = "TV-MA",
                isTvSHow = true
            ),

            MovieEntity(
                id = 30,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                title = "Hawkeye",
                tagLine = "This holiday season, the best gifts come with a bow.",
                releaseDate = "Nov 24, 2021",
                rating = 8.4,
                overview = """Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. 
                    |Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. 
                    |The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit."""
                    .trimMargin(),
                genres = listOf(
                    "Action & Adventure",
                    "Drama"
                ),
                episode = 6,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 31,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/avUmZDbbCcvnIFw0yrTM3A4CLlW.jpg",
                title = "Sword Snow Stride",
                tagLine = "",
                releaseDate = "Dec 15, 2021",
                rating = 6.2,
                overview = """Xu Xiao has risen to become King of Northern Liang at a time of great upheaval in China.
                    |Steppe kingdoms have risen with deadly intent, and rebellious uprisings threaten to destabilise the realm in the south. 
                    |Xu Xiao has managed to defeat them all. His unconventional, 
                    |free-spirited son and heir Xu Feng Nian has spent years on the road, honing his horse skills and fighting prowess. 
                    |Along the way, he meets the double sword-wielding martial arts expert Nan Gong Pu She, who becomes his confidante. 
                    |Also joining the royal household is a woman named Jiang Ni. At age 12, she joined the court of the Northern Liang. 
                    |She is the princess of a rival kingdom, and could be harbouring deep sentiments of revenge. When Xu Feng Nian has to take to the throne,
                    | some at court worry he will not be able to follow in the footsteps of his father. But things come to a head when the party has to travel 
                    | to another province to fight a decisive campaign, the result of which could determine the future of the realm."""
                    .trimMargin(),
                genres = listOf(
                    "Drama"
                ),
                episode = 38,
                certification = "",
                isTvSHow = true
            ),

            MovieEntity(
                id = 32,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iF8ai2QLNiHV4anwY1TuSGZXqfN.jpg",
                title = "Chucky",
                tagLine = "A classic coming of rage story.",
                releaseDate = "Oct 12, 2021",
                rating = 7.9,
                overview = """After a vintage Chucky doll turns up at a suburban yard sale, an idyllic American town is thrown into chaos as a series of horrifying murders begin to expose the town’s hypocrisies and secrets. 
                    |Meanwhile, the arrival of enemies — and allies — from Chucky’s past threatens to expose the truth behind the killings, as well as the demon doll’s untold origins."""
                    .trimMargin(),
                genres = listOf(
                    "Crime"
                ),
                episode = 8,
                certification = "TV-MA",
                isTvSHow = true
            ),

            MovieEntity(
                id = 33,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
                title = "The Walking Dead",
                tagLine = "Fight the dead. Fear the living.",
                releaseDate = "Oct 31, 2010",
                rating = 8.1,
                overview = """Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. 
                    |He sets out to find his family and encounters many other survivors along the way."""
                    .trimMargin(),
                genres = listOf(
                    "Action & Adventure",
                    "Drama",
                    "Sci-Fi & Fantasy"
                ),
                episode = 10,
                certification = "TV-MA",
                isTvSHow = true
            ),

            MovieEntity(
                id = 34,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7vjaCdMw15FEbXyLQTVa04URsPm.jpg",
                title = "The Witcher",
                tagLine = "Destiny is a beast.",
                releaseDate = "Dec 20, 2019",
                rating = 8.2,
                overview = """Geralt of Rivia, a mutated monster-hunter for hire, 
                    |journeys toward his destiny in a turbulent world where people often prove more wicked than beasts."""
                    .trimMargin(),
                genres = listOf(
                    "Sci-Fi & Fantasy",
                    "Drama",
                    "Action & Adventure"
                ),
                episode = 8,
                certification = "TV-MA",
                isTvSHow = true
            ),

            MovieEntity(
                id = 35,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg",
                title = "Squid Game",
                tagLine = "45.6 billion won is child's play.",
                releaseDate = "Nov 18, 2021",
                rating = 7.8,
                overview = """Hundreds of cash-strapped players accept a strange invitation to compete in children's games—with high stakes. But,
                    | a tempting prize awaits the victor."""
                    .trimMargin(),
                genres = listOf(
                    "Action & Adventure",
                    "Mystery",
                    "Drama"
                ),
                episode = 9,
                certification = "19",
                isTvSHow = true
            ),

            MovieEntity(
                id = 36,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                title = "Loki",
                tagLine = "Loki's time has come.",
                releaseDate = "Jun 09, 2021",
                rating = 8.2,
                overview = """After stealing the Tesseract during the events of “Avengers: Endgame,” 
                    |an alternate version of Loki is brought to the mysterious Time Variance Authority,
                    | a bureaucratic organization that exists outside of time and space and monitors the timeline. 
                    | They give Loki a choice: face being erased from existence due to being a “time 
                    | variant” or help fix the timeline and stop a greater threat."""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Sci-Fi & Fantasy"
                ),
                episode = 6,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 37,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                title = "The Falcon and the Winter Soldier",
                tagLine = "Honor the shield.",
                releaseDate = "March 19, 2021",
                rating = 7.8,
                overview = """Following the events of “Avengers: Endgame”, 
                    |the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a 
                    |global adventure that tests their abilities, and their patience."""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Action & Adventure",
                    "Sci-Fi & Fantasy"
                ),
                episode = 6,
                certification = "",
                isTvSHow = true
            ),

            MovieEntity(
                id = 38,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                title = "WandaVision",
                tagLine = "Experience a new vision of reality.",
                releaseDate = "January 15, 2021",
                rating = 8.4,
                overview = """Wanda Maximoff and Vision—two super-powered beings living idealized suburban
                    | lives—begin to suspect that everything is not as it seems."""
                    .trimMargin(),
                genres = listOf(
                    "Sci-Fi & Fantasy",
                    "Mystery",
                    "Drama"
                ),
                episode = 9,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 39,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/z0iCS5Znx7TeRwlYSd4c01Z0lFx.jpg",
                title = "The D'Amelio Show",
                tagLine = "Followers come and go but family is forever.",
                releaseDate = "Sep 03, 2021",
                rating = 9.4,
                overview = """From relative obscurity and a seemingly normal life, to overnight success and thrust into the Hollywood limelight overnight, 
                    |the D’Amelios are faced with new challenges and opportunities they could not have imagined."""
                    .trimMargin(),
                genres = listOf(
                    "Reality"
                ),
                episode = 8,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 40,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ekZobS8isE6mA53RAiGDG93hBxL.jpg",
                title = "Lucifer",
                tagLine = "All bad things must come to an end.",
                releaseDate = "Jan 25, 2016",
                rating = 8.5,
                overview = """Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, 
                    |where he has teamed up with LAPD detective Chloe Decker to take down criminals. 
                    |But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape."""
                    .trimMargin(),
                genres = listOf(
                    "Crime",
                    "Sci-Fi & Fantasy"
                ),
                episode = 10,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 41,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                title = "Game of Thrones",
                tagLine = "Winter Is Coming",
                releaseDate = "Apr 17, 2011",
                rating = 8.4,
                overview = """Seven noble families fight for control of the mythical land of Westeros. 
                    |Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north.
                    | Amidst the war, a neglected military order of misfits, the Night's Watch, 
                    |is all that stands between the realms of men and icy horrors beyond."""
                    .trimMargin(),
                genres = listOf(
                    "Sci-Fi & Fantasy",
                    "Drama",
                    "Action & Adventure"
                ),
                episode = 6,
                certification = "TV-14",
                isTvSHow = true
            ),

            MovieEntity(
                id = 42,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5NYdSAnDVIXePrSG2dznHdiibMk.jpg",
                title = "Hellbound",
                tagLine = "Your time is up.",
                releaseDate = "Nov 19, 2021",
                rating = 7.6,
                overview = """Unearthly beings deliver bloody condemnations, 
                    |sending individuals to hell and giving rise to a religious group founded on the idea of divine justice."""
                    .trimMargin(),
                genres = listOf(
                    "Drama",
                    "Crime",
                    "Mystery",
                    "Sci-Fi & Fantasy",
                ),
                episode = 6,
                certification = "19",
                isTvSHow = true
            )
        )
    }
}