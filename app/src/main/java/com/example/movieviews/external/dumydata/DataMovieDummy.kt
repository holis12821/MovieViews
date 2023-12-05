package com.example.movieviews.external.dumydata

import com.example.movieviews.data.models.BaseResponse
import com.example.movieviews.data.models.MovieResult

/**
 * Singleton object to get and produce data dummy movie*/
object DataMovieDummy {

    fun getMovies(): BaseResponse<List<MovieResult>> {
        return BaseResponse(
            status_message = "Error to get data movie",
            results = mutableListOf(
                MovieResult(
                    id = 634649,
                    originalTitle = "Spider-Man: No Way Home",
                    overview = """Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. 
                        |When he asks for help from Doctor Strange the stakes become even more dangerous, 
                        |forcing him to discover what it truly means to be Spider-Man.""".trimMargin(),
                    posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                    releaseDate = "2021-12-15",
                    voteAverage = 8.4,
                ),
                MovieResult(
                    id = 524434,
                    originalTitle = "Eternals",
                    overview = """The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. 
                        |When an unexpected tragedy forces them out of the shadows, 
                        |they are forced to reunite against mankind’s most ancient enemy, the Deviants.""".trimMargin(),
                    posterPath = "/k2twTjSddgLc1oFFHVibfxp2kQV.jpg",
                    releaseDate = "2021-11-03",
                    voteAverage = 7.2
                ),

                MovieResult(
                    id = 568124,
                    originalTitle = "Encanto",
                    overview = """The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, 
                        |in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the
                        | Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel.
                        | But when she discovers that the magic surrounding the Encanto is in danger, 
                        |Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.""".trimMargin(),
                    posterPath = "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",
                    releaseDate = "2021-11-24",
                    voteAverage = 7.8
                ),

                MovieResult(
                    id = 585083,
                    originalTitle = "Hotel Transylvania: Transformania",
                    overview = """When Van Helsing's mysterious invention, the \"Monsterfication Ray,\" goes haywire, Drac and his monster pals are all transformed into humans, 
                        |and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late,
                        | and before they drive each other crazy""".trimMargin(),
                    posterPath = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                    releaseDate = "2021-12-15",
                    voteAverage = 8.4
                ),

                MovieResult(
                    id = 774825,
                    originalTitle = "The Ice Age Adventures of Buck Wild",
                    overview = """The fearless one-eyed weasel Buck teams up with mischievous possum brothers Crash & 
                        |Eddie as they head off on a new adventure into Buck's home: The Dinosaur World.""".trimMargin(),
                    posterPath = "/zzXFM4FKDG7l1ufrAkwQYv2xvnh.jpg",
                    releaseDate = "2022-01-28",
                    voteAverage = 7.5
                ),

                MovieResult(
                    id = 438695,
                    originalTitle = "Sing 2",
                    overview = """Buster and his new cast now have their sights set on debuting a new show at the Crystal Tower Theater in glamorous Redshore City.
                        | But with no connections, he and his singers must sneak into the Crystal Entertainment offices, run by the ruthless wolf mogul Jimmy Crystal, 
                        | where the gang pitches the ridiculous idea of casting the lion rock legend Clay Calloway in their show. 
                        |Buster must embark on a quest to find the now-isolated Clay and persuade him to return to the stage.""".trimMargin(),
                    posterPath = "/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg",
                    releaseDate = "2021-12-01",
                    voteAverage = 8.3
                ),

                MovieResult(
                    id = 425909,
                    originalTitle = "Ghostbusters: Afterlife",
                    overview = """Ghostbusters: Afterlife",
                        |"overview": "When a single mom and her two kids arrive in a small town, they begin to discover their connection 
                        |to the original Ghostbusters and the 
                        |secret legacy their grandfather left behind.""".trimMargin(),
                    posterPath = "/sg4xJaufDiQl7caFEskBtQXfD4x.jpg",
                    releaseDate = "2021-11-11",
                    voteAverage = 7.7
                ),

                MovieResult(
                    id = 860623,
                    originalTitle = "Last Man Down",
                    overview = """After civilization succumbs to a deadly pandemic and his wife is murdered, a special forces soldier abandons his duty and becomes a hermit in the Nordic wilderness.
                        | Years later, a wounded woman appears on his doorstep. She's escaped from a lab and her pursuers believe her blood is the key to a worldwide cure. He's hesitant to get involved, 
                        | but all doubts are cast aside when he discovers her pursuer is none other than Commander Stone, 
                        |the man that murdered his wife some years ago.""".trimMargin(),
                    posterPath = "/4B7liCxNCZIZGONmAMkCnxVlZQV.jpg",
                    releaseDate = "2021-10-19",
                    voteAverage = 6.6
                ),

                MovieResult(
                    id = 624860,
                    originalTitle = "The Matrix Resurrections",
                    overview = """Plagued by strange memories, Neo's life takes an unexpected turn when he finds himself back inside the Matrix""".trimMargin(),
                    posterPath = "/8c4a8kE7PizaGQQnditMmI1xbRp.jpg",
                    releaseDate = "2021-12-16",
                    voteAverage = 6.8
                ),

                MovieResult(
                    id = 811592,
                    originalTitle = "One Shot",
                    overview = """An elite squad of Navy SEALs, on a covert mission to transport a prisoner off a CIA black site island prison, 
                        |are trapped when insurgents attack while trying to rescue the same prisoner.""".trimMargin(),
                    posterPath = "/3OXiTjU30gWtqxmx4BU9RVp2OTv.jpg",
                    releaseDate = "2021-11-05",
                    voteAverage = 6.8
                )
            )
        )
    }


    fun getTvShow(): BaseResponse<List<MovieResult>> {
        return BaseResponse(
            status_message = "Error to get data movie",
            results = mutableListOf(
                MovieResult(
                    id = 99966,
                    originalName = "지금 우리 학교는",
                    overview = """A high school becomes ground zero for a zombie virus outbreak. 
                        |Trapped students must fight their way out — or turn into one of the rabid infected.""".trimMargin(),
                    posterPath = "/ze4lhw0oLBHYmlM2KuZjBg0Sq6H.jpg",
                    voteAverage = 8.7
                ),
                MovieResult(
                    id = 110492,
                    originalName = "Peacemaker",
                    overview = """The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, 
                        |no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.""".trimMargin(),
                    posterPath = "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                    voteAverage = 8.7
                ),

                MovieResult(
                    id = 85552,
                    originalName ="Euphoria",
                    overview = """A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.""".trimMargin(),
                    posterPath = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                    voteAverage = 8.4
                ),

                MovieResult(
                    id =  966,
                    originalName= "Hollyoaks",
                    overview = """The daily soap that follows the loves, lives and misdemeanours of a group of people living in the Chester village of
                        | Hollyoaks where anything could, and frequently does, happen...""".trimMargin(),
                    posterPath = "/bpmLMZP3M1vLujPqHnOTnKVjRJY.jpg",
                    voteAverage = 5.2
                ),

                MovieResult(
                    id = 6455,
                    originalName = "Chain Reaction",
                    overview = """Chain Reaction is an American game show created by Bob Stewart, in which players compete to form chains composed of two-word phrases.\n\nThe show aired three separate runs: 
                        |Bill Cullen hosted the original series on NBC from January 14 to June 20, 1980. The second version aired on the USA Network from September 29, 1986 to December 27, 1991 and was hosted first by Blake 
                        |Emmons and later by Geoff Edwards. Another version, 
                        |hosted by Dylan Lane, premiered on August 1, 2006 on GSN. This version aired its final original episode on June 9, 2007 but has continued to air in reruns since,
                        | currently airing Mondays through Fridays at 3:00 PM and 3:30 PM ET on GSN. Starting August 2, 2013, it will air Fridays at 8:00 PM to 10:00 PM on GSN.""".trimMargin(),
                    posterPath = "/xhjoXm1WEvfQGYBGXKk8xk75z6s.jpg",
                    voteAverage = 1.5
                ),

                MovieResult(
                    id = 115036,
                    originalName = "The Book of Boba Fett",
                    overview = """Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by 
                        |Jabba the Hutt and his crime syndicate.""".trimMargin(),
                    posterPath = "/emuCvPY9ztbTM4ct4zqEQXraZli.jpg",
                    voteAverage = 8.2
                ),

                MovieResult(
                    id = 63452,
                    originalName = "Wer weiß denn sowas?",
                    overview = """""".trimMargin(),
                    posterPath = "/abKjah96esLWObidBcWmvKJv61E.jpg",
                    voteAverage = 7.7
                ),

                MovieResult(
                    id =  114885,
                    originalTitle = "Big Brother",
                    overview = """""".trimMargin(),
                    posterPath = "/zmXxCP0op3PoCSiqXkSKDySbxe8.jpg",
                    voteAverage = 9.7
                ),

                MovieResult(
                    id = 135753,
                    originalTitle = "사랑의 꽈배기",
                    overview = """A drama depicting a sweet twist in love between the parents and children of three families around
                        | the love of two main characters.""".trimMargin(),
                    posterPath = "/5bTF522eYn6g6r7aYqFpTZzmQq6.jpg",
                    voteAverage = 4.5
                ),

                MovieResult(
                    id = 153748,
                    originalTitle = "Big Brother Famosos",
                    overview = """Big Brother Famosos is the celebrity version of Big Brother Portugal.""".trimMargin(),
                    posterPath = "/ynFd1Xmr2r05qPqalNZnh2uxuJ0.jpg",
                    voteAverage = 4.5
                )
            )
        )
    }

}