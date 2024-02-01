package com.marcelmalewski.focustimetracker.controller;

import com.marcelmalewski.focustimetracker.model.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Tag(name = "mers v1", description = "Gamers API v1")
public class StatusController {
	public static final int PAGE_LENGHT = 10;
	private static final List<Status> statuses = List.of(
		new Status(1, "@user123", "John Smith", "Just enjoyed a delicious cup of coffee at my favorite cafe. ☕️ #CoffeeLover"),
		new Status(2, "@naturelover22", "Nature Lover", "Spent the weekend hiking in the mountains, surrounded by breathtaking views. 🌲🏞️ #Nature"),
		new Status(3, "@techgeek", "Tech Geek", "Exciting news in the tech world today! The new smartphone models are coming soon. 📱 #TechNews"),
		new Status(4, "@bookworm", "Bookworm", "Started reading a new book last night. Can't put it down! 📚 #BookRecommendation"),
		new Status(5, "@foodie", "Foodie", "Tried a new recipe for homemade pizza tonight. Turned out better than expected! 🍕 #Foodie"),
		new Status(6, "@traveler", "Wanderlust", "Dreaming of my next travel adventure. Where should I go next? ✈️ #TravelGoals"),
		new Status(7, "@musiclover", "Music Lover", "Listening to my favorite band's new album on repeat. Pure musical bliss! 🎶 #Music"),
		new Status(8, "@fitnessfanatic", "Fitness Fanatic", "Morning workout complete. Feeling strong and energized! 💪 #Fitness"),
		new Status(9, "@movielover", "Movie Buff", "Movie night with friends! Popcorn, cozy blankets, and a great film. 🍿🎬 #MovieNight"),
		new Status(10, "@gamer", "Gamer", "Just achieved a new high score in my favorite video game. Victory dance time! 🎮🕹️ #Gaming"),
		new Status(11, "@artenthusiast", "Art Enthusiast", "Visited an art gallery today and was inspired by the amazing artwork on display. 🎨 #Art"),
		new Status(12, "@petlover", "Pet Lover", "Spent the day at the park with my furry friend. Dogs make life better! 🐶❤️ #DogLove"),
		new Status(13, "@positivevibes", "Positive Vibes", "Sending positive vibes and smiles to everyone today. 😊✨ #Positivity"),
		new Status(14, "@fashionista", "Fashionista", "Fashion is my passion! Loving the latest trends and styles. 👗👠 #Fashion"),
		new Status(15, "@cookingenthusiast", "Cooking Enthusiast", "Experimented with a new dessert recipe. It's a sweet success! 🍰👩‍🍳 #Baking"),
		new Status(16, "@historybuff", "History Buff", "Delving into a historical documentary marathon today. Learning is fun! 📜📺 #History"),
		new Status(17, "@photographer", "Photographer", "Captured a stunning sunset photo by the beach. Nature's beauty never disappoints. 🌅📷 #Photography"),
		new Status(18, "@inspiration", "Inspiration Seeker", "Finding inspiration in the little things. Gratitude for life's blessings. 🙏✨ #Gratitude"),
		new Status(19, "@gardener", "Gardener", "Gardening therapy! Planting colorful flowers and watching them bloom. 🌸🌿 #Gardening"),
		new Status(20, "@comedylover", "Comedy Lover", "Laughter is the best medicine. Enjoying a comedy show tonight. 😂🤣 #Comedy"),
		new Status(21, "@codinggeek", "Coding Geek", "Solving complex coding problems is my idea of fun. #Programming"),
		new Status(22, "@yogaenthusiast", "Yoga Enthusiast", "Morning yoga session to start the day with peace and balance. 🧘‍♀️🌞 #Yoga"),
		new Status(23, "@volunteer", "Volunteer", "Spent the weekend volunteering at a local shelter. Small acts of kindness can make a big difference. ❤️ #Volunteer"),
		new Status(24, "@startupfounder", "Startup Founder", "Building the future one step at a time. The journey is the destination. 🚀 #Entrepreneur"),
		new Status(25, "@poetrylover", "Poetry Lover", "Penning down my thoughts in poetic verses. Expressing the heart's deepest emotions through words. 📝 #Poetry"),
		new Status(26, "@DIYenthusiast", "DIY Enthusiast", "Crafting and creating DIY projects bring so much joy. Today, it's a homemade candle! 🕯️ #DIY"),
		new Status(27, "@beachbum", "Beach Lover", "Salt in the air, sand in my hair. A perfect day at the beach! 🏖️ #BeachLife"),
		new Status(28, "@environmentalist", "Environmentalist", "Let's protect our planet. Reduce, reuse, recycle! 🌍🌱 #Environment"),
		new Status(29, "@bookclub", "Book Club Member", "Discussing the latest book club pick with friends. Bookworms unite! 📖 #BookClub"),
		new Status(30, "@coffeeaddict", "Coffee Addict", "Coffee is my love language. Cheers to a fresh brew! ☕️❤️ #Coffee"),
		new Status(31, "@wanderer", "Wanderer", "Exploring new places, meeting new people, and collecting memories along the way. 🌎✈️ #Travel"),
		new Status(32, "@healthyliving", "Healthy Living", "Eating clean and staying active for a healthier, happier life. 🥗🏋️‍♀️ #HealthyLiving"),
		new Status(33, "@moviecritic", "Movie Critic", "Just watched a thought-provoking film. The performances were outstanding! 🎥🍿 #FilmReview"),
		new Status(34, "@animalrights", "Animal Rights Advocate", "Every creature deserves love and respect. Let's protect our furry friends! 🐾❤️ #AnimalRights"),
		new Status(35, "@stargazer", "Stargazer", "Tonight's sky is a canvas of stars. Finding peace in the cosmos. 🌌✨ #Astronomy"),
		new Status(36, "@motivation", "Motivation", "Believe in yourself and your dreams. You're capable of amazing things. 💪🌟 #Motivation"),
		new Status(37, "@chefathome", "Chef at Home", "Cooking up a storm in my kitchen today. Who wants to join me for dinner? 🍽️🍷 #HomeCooking"),
		new Status(38, "@inspiration", "Inspiration Seeker", "Finding inspiration in the little things. Gratitude for life's blessings. 🙏✨ #Gratitude"),
		new Status(39, "@gardener", "Gardener", "Gardening therapy! Planting colorful flowers and watching them bloom. 🌸🌿 #Gardening"),
		new Status(40, "@comedylover", "Comedy Lover", "Laughter is the best medicine. Enjoying a comedy show tonight. 😂🤣 #Comedy")
	);

	@Operation(summary = "Fiall gamers public info")
	@GetMapping("/feed")
	public String getFeed(Model model) {
		model.addAttribute("statuses", statuses.subList(0, PAGE_LENGHT));
		return "feed";
	}
}
