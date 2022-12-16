package com.example.group13backend.config;

import com.example.group13backend.db.models.Item;
import com.example.group13backend.db.repository.CategoryRepository;
import com.example.group13backend.db.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {
  private final CategoryRepository categoryRepository;

  public ItemConfig(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Autowired
  @Bean
  CommandLineRunner itemCommandLineRunner(ItemRepository repository) {
    // String name, String description, Integer quantity, Double price;
    return args -> {
      // produce
      Item apple =
          new Item("Apple", "A juicy red apple", 5, 0.99, categoryRepository.getReferenceById(1L));
      Item banana =
          new Item("Banana", "A yellow banana", 7, 0.59, categoryRepository.getReferenceById(1L));
      Item orange =
          new Item("Orange", "A juicy orange", 10, 0.89, categoryRepository.getReferenceById(1L));
      Item strawberry =
          new Item(
              "Strawberry", "A red strawberry", 12, 0.39, categoryRepository.getReferenceById(1L));
      Item raspberry =
          new Item(
              "Raspberry", "A red raspberry", 8, 0.49, categoryRepository.getReferenceById(1L));
      Item blueberry =
          new Item(
              "Blueberry", "A blue blueberry", 6, 0.69, categoryRepository.getReferenceById(1L));
      Item blackberry =
          new Item(
              "Blackberry", "A black blackberry", 4, 0.79, categoryRepository.getReferenceById(1L));
      Item avocado =
          new Item("Avocado", "A ripe avocado", 3, 0.99, categoryRepository.getReferenceById(1L));
      Item grapefruit =
          new Item(
              "Grapefruit", "A pink grapefruit", 2, 0.79, categoryRepository.getReferenceById(1L));
      Item kiwi =
          new Item("Kiwi", "A green kiwi", 9, 0.69, categoryRepository.getReferenceById(1L));

      // meatAnsSeafood
      Item chicken =
          new Item("Chicken", "A whole chicken", 1, 3.99, categoryRepository.getReferenceById(2L));
      Item beef =
          new Item(
              "Beef", "A pound of ground beef", 2, 5.99, categoryRepository.getReferenceById(2L));
      Item salmon =
          new Item("Salmon", "A salmon fillet", 3, 6.99, categoryRepository.getReferenceById(2L));
      Item shrimp =
          new Item(
              "Shrimp",
              "A pound of peeled and deveined shrimp",
              4,
              8.99,
              categoryRepository.getReferenceById(2L));
      Item scallops =
          new Item(
              "Scallops", "A pound of scallops", 5, 9.99, categoryRepository.getReferenceById(2L));
      Item crab =
          new Item(
              "Crab", "A pound of crab meat", 6, 10.99, categoryRepository.getReferenceById(2L));
      Item lobster =
          new Item("Lobster", "A whole lobster", 7, 12.99, categoryRepository.getReferenceById(2L));
      Item mussels =
          new Item(
              "Mussels", "A pound of mussels", 8, 5.99, categoryRepository.getReferenceById(2L));
      Item clams =
          new Item("Clams", "A pound of clams", 9, 6.99, categoryRepository.getReferenceById(2L));
      Item oysters =
          new Item("Oysters", "A dozen oysters", 10, 7.99, categoryRepository.getReferenceById(2L));

      // dairy
      Item milk =
          new Item("Milk", "A gallon of milk", 5, 2.99, categoryRepository.getReferenceById(3L));
      Item yogurt =
          new Item(
              "Yogurt",
              "A cup of vanilla yogurt",
              6,
              0.99,
              categoryRepository.getReferenceById(3L));
      Item cheese =
          new Item(
              "Cheese",
              "A block of cheddar cheese",
              7,
              3.99,
              categoryRepository.getReferenceById(3L));
      Item butter =
          new Item(
              "Butter",
              "A stick of unsalted butter",
              8,
              1.99,
              categoryRepository.getReferenceById(3L));
      Item iceCream =
          new Item(
              "Ice Cream",
              "A pint of vanilla ice cream",
              9,
              2.99,
              categoryRepository.getReferenceById(3L));
      Item sourCream =
          new Item(
              "Sour Cream",
              "A cup of sour cream",
              10,
              1.49,
              categoryRepository.getReferenceById(3L));
      Item cottageCheese =
          new Item(
              "Cottage Cheese",
              "A cup of cottage cheese",
              11,
              1.79,
              categoryRepository.getReferenceById(3L));
      Item creamCheese =
          new Item(
              "Cream Cheese",
              "A block of cream cheese",
              12,
              1.99,
              categoryRepository.getReferenceById(3L));
      Item eggs =
          new Item("Eggs", "A dozen large eggs", 13, 2.29, categoryRepository.getReferenceById(3L));
      Item milkShake =
          new Item(
              "Milk Shake",
              "A chocolate milk shake",
              14,
              2.99,
              categoryRepository.getReferenceById(3L));
      Item strawberryYogurt =
          new Item(
              "Strawberry Yogurt",
              "A cup of strawberry yogurt",
              15,
              1.99,
              categoryRepository.getReferenceById(3L));

      // frozenFoods
      Item pizza =
          new Item("Pizza", "A cheese pizza", 2, 5.99, categoryRepository.getReferenceById(4L));
      Item burritos =
          new Item(
              "Burritos",
              "A pack of beef burritos",
              3,
              4.99,
              categoryRepository.getReferenceById(4L));
      Item chickenNuggets =
          new Item(
              "Chicken Nuggets",
              "A pack of chicken nuggets",
              4,
              3.99,
              categoryRepository.getReferenceById(4L));
      Item iceCreamSandwich =
          new Item(
              "Ice Cream Sandwich",
              "An ice cream sandwich",
              5,
              1.99,
              categoryRepository.getReferenceById(4L));
      Item frozenDinner =
          new Item(
              "Frozen Dinner",
              "A frozen chicken dinner",
              6,
              3.99,
              categoryRepository.getReferenceById(4L));
      Item frozenPizza =
          new Item(
              "Frozen Pizza",
              "A pepperoni frozen pizza",
              7,
              5.99,
              categoryRepository.getReferenceById(4L));
      Item frozenYogurt =
          new Item(
              "Frozen Yogurt",
              "A cup of blueberry frozen yogurt",
              8,
              1.99,
              categoryRepository.getReferenceById(4L));
      Item iceCreamCone =
          new Item(
              "Ice Cream Cone",
              "A vanilla ice cream cone",
              9,
              1.49,
              categoryRepository.getReferenceById(4L));
      Item iceCreamBar =
          new Item(
              "Ice Cream Bar",
              "A chocolate-covered ice cream bar",
              10,
              1.29,
              categoryRepository.getReferenceById(4L));
      Item frozenWaffles =
          new Item(
              "Frozen Waffles",
              "A pack of frozen waffles",
              11,
              1.99,
              categoryRepository.getReferenceById(4L));

      // bakery
      Item bread =
          new Item(
              "Bread", "A loaf of white bread", 1, 2.29, categoryRepository.getReferenceById(5L));
      Item bagel =
          new Item("Bagel", "A sesame bagel", 2, 1.29, categoryRepository.getReferenceById(5L));
      Item croissant =
          new Item(
              "Croissant", "A butter croissant", 3, 1.99, categoryRepository.getReferenceById(5L));
      Item muffin =
          new Item(
              "Muffin", "A blueberry muffin", 4, 1.59, categoryRepository.getReferenceById(5L));
      Item donut =
          new Item("Donut", "A glazed donut", 5, 0.99, categoryRepository.getReferenceById(5L));
      Item cinnamonRoll =
          new Item(
              "Cinnamon Roll", "A cinnamon roll", 6, 1.29, categoryRepository.getReferenceById(5L));
      Item coffeeCake =
          new Item(
              "Coffee Cake",
              "A slice of coffee cake",
              7,
              1.99,
              categoryRepository.getReferenceById(5L));
      Item freshBrownies =
          new Item(
              "Brownie",
              "A fresh chocolate brownie",
              8,
              1.29,
              categoryRepository.getReferenceById(5L));
      Item freshCookies =
          new Item(
              "Cookies",
              "A pack of fresh chocolate chip cookies",
              9,
              1.99,
              categoryRepository.getReferenceById(5L));
      Item cupcakes =
          new Item(
              "Cupcakes",
              "A pack of vanilla cupcakes",
              10,
              2.49,
              categoryRepository.getReferenceById(5L));

      // beverages
      Item soda =
          new Item(
              "Soda", "A 2-liter bottle of soda", 3, 1.99, categoryRepository.getReferenceById(6L));
      Item water =
          new Item(
              "Water",
              "A 24-pack of bottled water",
              4,
              3.99,
              categoryRepository.getReferenceById(6L));
      Item juice =
          new Item(
              "Juice",
              "A gallon of orange juice",
              5,
              2.99,
              categoryRepository.getReferenceById(6L));
      Item coffee =
          new Item(
              "Coffee",
              "A 12-ounce bag of coffee beans",
              6,
              7.99,
              categoryRepository.getReferenceById(6L));
      Item tea =
          new Item(
              "Tea", "A box of black tea bags", 7, 3.99, categoryRepository.getReferenceById(6L));
      Item beer =
          new Item("Beer", "A 6-pack of beer", 8, 8.99, categoryRepository.getReferenceById(6L));
      Item wine =
          new Item(
              "Wine", "A bottle of red wine", 9, 12.99, categoryRepository.getReferenceById(6L));
      Item cocktails =
          new Item(
              "Cocktails",
              "A pack of pre-made cocktails",
              10,
              9.99,
              categoryRepository.getReferenceById(6L));
      Item smoothies =
          new Item(
              "Smoothies",
              "A pack of pre-made smoothies",
              11,
              4.99,
              categoryRepository.getReferenceById(6L));
      Item energyDrinks =
          new Item(
              "Energy Drinks",
              "A pack of energy drinks",
              12,
              5.99,
              categoryRepository.getReferenceById(6L));
      Item sportsDrinks =
          new Item(
              "Sports Drinks",
              "A pack of sports drinks",
              13,
              4.99,
              categoryRepository.getReferenceById(6L));
      Item kombucha =
          new Item(
              "Kombucha",
              "A bottle of kombucha",
              14,
              3.99,
              categoryRepository.getReferenceById(6L));

      // cannedGoods
      Item pasta =
          new Item("Pasta", "A box of spaghetti", 1, 0.99, categoryRepository.getReferenceById(7L));
      Item beans =
          new Item(
              "Beans", "A can of black beans", 2, 0.79, categoryRepository.getReferenceById(7L));
      Item soup =
          new Item(
              "Soup",
              "A can of chicken noodle soup",
              3,
              0.99,
              categoryRepository.getReferenceById(7L));
      Item cannedVeggies =
          new Item(
              "Canned Veggies", "A can of corn", 4, 0.59, categoryRepository.getReferenceById(7L));
      Item cannedFruit =
          new Item(
              "Canned Fruit",
              "A can of pineapple chunks",
              5,
              0.79,
              categoryRepository.getReferenceById(7L));
      Item crackers =
          new Item(
              "Crackers", "A box of crackers", 6, 1.99, categoryRepository.getReferenceById(7L));
      Item chips =
          new Item(
              "Chips", "A bag of potato chips", 7, 1.99, categoryRepository.getReferenceById(7L));
      Item granolaBars =
          new Item(
              "Granola Bars",
              "A box of granola bars",
              8,
              1.99,
              categoryRepository.getReferenceById(7L));
      Item cereal =
          new Item("Cereal", "A box of cereal", 9, 2.99, categoryRepository.getReferenceById(7L));
      Item rice =
          new Item(
              "Rice", "A bag of white rice", 10, 1.99, categoryRepository.getReferenceById(7L));

      // condiments
      Item ketchup =
          new Item(
              "Ketchup", "A bottle of ketchup", 1, 0.99, categoryRepository.getReferenceById(8L));
      Item mustard =
          new Item(
              "Mustard", "A bottle of mustard", 2, 0.79, categoryRepository.getReferenceById(8L));
      Item mayonnaise =
          new Item(
              "Mayonnaise",
              "A jar of mayonnaise",
              3,
              1.49,
              categoryRepository.getReferenceById(8L));
      Item hotSauce =
          new Item(
              "Hot Sauce",
              "A bottle of hot sauce",
              4,
              0.99,
              categoryRepository.getReferenceById(8L));
      Item saladDressing =
          new Item(
              "Salad Dressing",
              "A bottle of ranch dressing",
              5,
              1.99,
              categoryRepository.getReferenceById(8L));
      Item barbecueSauce =
          new Item(
              "Barbecue Sauce",
              "A bottle of barbecue sauce",
              6,
              1.49,
              categoryRepository.getReferenceById(8L));
      Item soySauce =
          new Item(
              "Soy Sauce",
              "A bottle of soy sauce",
              7,
              1.99,
              categoryRepository.getReferenceById(8L));
      Item hoisinSauce =
          new Item(
              "Hoisin Sauce",
              "A bottle of hoisin sauce",
              8,
              1.79,
              categoryRepository.getReferenceById(8L));
      Item teriyakiSauce =
          new Item(
              "Teriyaki Sauce",
              "A bottle of teriyaki sauce",
              9,
              1.99,
              categoryRepository.getReferenceById(8L));
      Item salsa =
          new Item("Salsa", "A jar of salsa", 10, 1.49, categoryRepository.getReferenceById(8L));
      Item steakSauce =
          new Item(
              "Steak Sauce",
              "A bottle of steak sauce",
              11,
              1.99,
              categoryRepository.getReferenceById(8L));
      Item marinaraSauce =
          new Item(
              "Marinara Sauce",
              "A jar of marinara sauce",
              12,
              1.49,
              categoryRepository.getReferenceById(8L));
      Item hotMustard =
          new Item(
              "Hot Mustard",
              "A jar of hot mustard",
              13,
              0.99,
              categoryRepository.getReferenceById(8L));
      Item worcestershireSauce =
          new Item(
              "Worcestershire Sauce",
              "A bottle of Worcestershire sauce",
              14,
              1.79,
              categoryRepository.getReferenceById(8L));

      // personalCare
      Item toiletPaper =
          new Item(
              "Toilet Paper",
              "A 12-pack of toilet paper",
              1,
              5.99,
              categoryRepository.getReferenceById(9L));
      Item paperTowels =
          new Item(
              "Paper Towels",
              "A 6-pack of paper towels",
              2,
              3.99,
              categoryRepository.getReferenceById(9L));
      Item dishSoap =
          new Item(
              "Dish Soap",
              "A bottle of dish soap",
              3,
              0.99,
              categoryRepository.getReferenceById(9L));
      Item laundryDetergent =
          new Item(
              "Laundry Detergent",
              "A bottle of liquid laundry detergent",
              4,
              4.99,
              categoryRepository.getReferenceById(9L));
      Item allPurposeCleaner =
          new Item(
              "All-Purpose Cleaner",
              "A bottle of all-purpose cleaner",
              5,
              1.99,
              categoryRepository.getReferenceById(9L));
      Item trashBags =
          new Item(
              "Trash Bags",
              "A box of trash bags",
              6,
              2.99,
              categoryRepository.getReferenceById(9L));
      Item lightBulbs =
          new Item(
              "Light Bulbs",
              "A pack of light bulbs",
              7,
              2.99,
              categoryRepository.getReferenceById(9L));
      Item airFreshener =
          new Item(
              "Air Freshener",
              "An air freshener",
              8,
              0.99,
              categoryRepository.getReferenceById(9L));
      Item dishwasherDetergent =
          new Item(
              "Dishwasher Detergent",
              "A box of dishwasher detergent",
              9,
              3.99,
              categoryRepository.getReferenceById(9L));
      Item bleach =
          new Item(
              "Bleach", "A bottle of bleach", 10, 0.99, categoryRepository.getReferenceById(9L));
      Item sponges =
          new Item(
              "Sponges", "A pack of sponges", 11, 1.99, categoryRepository.getReferenceById(9L));
      Item aluminumFoil =
          new Item(
              "Aluminum Foil",
              "A roll of aluminum foil",
              12,
              1.99,
              categoryRepository.getReferenceById(9L));
      Item glassCleaner =
          new Item(
              "Glass Cleaner",
              "A bottle of glass cleaner",
              13,
              1.99,
              categoryRepository.getReferenceById(9L));
      Item towels =
          new Item("Towels", "A pack of towels", 14, 7.99, categoryRepository.getReferenceById(9L));
      Item napkins =
          new Item(
              "Napkins", "A pack of napkins", 15, 1.99, categoryRepository.getReferenceById(9L));
      Item tissues =
          new Item(
              "Tissues", "A box of tissues", 16, 1.99, categoryRepository.getReferenceById(9L));
      Item matches =
          new Item(
              "Matches", "A box of matches", 17, 0.99, categoryRepository.getReferenceById(9L));
      Item laundryBasket =
          new Item(
              "Laundry Basket",
              "A laundry basket",
              18,
              7.99,
              categoryRepository.getReferenceById(9L));
      Item dishDrainer =
          new Item(
              "Dish Drainer", "A dish drainer", 19, 6.99, categoryRepository.getReferenceById(9L));
      Item broom = new Item("Broom", "A broom", 20, 4.99, categoryRepository.getReferenceById(9L));

      // snacks
      Item candy =
          new Item("Candy", "A bag of candy", 2, 1.99, categoryRepository.getReferenceById(10L));
      Item gum =
          new Item("Gum", "A pack of gum", 3, 0.99, categoryRepository.getReferenceById(10L));
      Item trailMix =
          new Item(
              "Trail Mix", "A bag of trail mix", 4, 2.99, categoryRepository.getReferenceById(10L));
      Item cookies =
          new Item(
              "Cookies", "A pack of cookies", 6, 1.99, categoryRepository.getReferenceById(10L));
      Item jerky =
          new Item("Jerky", "A pack of jerky", 7, 3.99, categoryRepository.getReferenceById(10L));
      Item energyBars =
          new Item(
              "Energy Bars",
              "A box of energy bars",
              8,
              2.99,
              categoryRepository.getReferenceById(10L));
      Item popcorn =
          new Item(
              "Popcorn", "A bag of popcorn", 9, 1.99, categoryRepository.getReferenceById(10L));
      Item fruitSnacks =
          new Item(
              "Fruit Snacks",
              "A box of fruit snacks",
              10,
              1.99,
              categoryRepository.getReferenceById(10L));
      Item pretzels =
          new Item(
              "Pretzels", "A bag of pretzels", 12, 1.99, categoryRepository.getReferenceById(10L));
      Item cheesePuffs =
          new Item(
              "Cheese Puffs",
              "A bag of cheese puffs",
              13,
              1.99,
              categoryRepository.getReferenceById(10L));
      Item nuts =
          new Item(
              "Nuts", "A bag of mixed nuts", 14, 2.99, categoryRepository.getReferenceById(10L));
      Item beefJerky =
          new Item(
              "Beef Jerky",
              "A pack of beef jerky",
              16,
              3.99,
              categoryRepository.getReferenceById(10L));
      Item candyBars =
          new Item(
              "Candy Bars",
              "A pack of candy bars",
              17,
              1.99,
              categoryRepository.getReferenceById(10L));
      Item fruitCups =
          new Item(
              "Fruit Cups",
              "A pack of fruit cups",
              18,
              1.99,
              categoryRepository.getReferenceById(10L));
      Item riceCakes =
          new Item(
              "Rice Cakes",
              "A pack of rice cakes",
              19,
              1.49,
              categoryRepository.getReferenceById(10L));
      Item granola =
          new Item(
              "Granola", "A bag of granola", 20, 2.49, categoryRepository.getReferenceById(10L));

      // deli
      Item deliMeat =
          new Item(
              "Deli Meat",
              "A pound of deli meat",
              1,
              5.99,
              categoryRepository.getReferenceById(11L));
      Item deliCheese =
          new Item(
              "Deli Cheese",
              "A pound of deli cheese",
              2,
              5.99,
              categoryRepository.getReferenceById(11L));
      Item hummus =
          new Item(
              "Hummus", "A container of hummus", 3, 3.99, categoryRepository.getReferenceById(11L));
      Item chickenSalad =
          new Item(
              "Chicken Salad",
              "A container of chicken salad",
              4,
              3.99,
              categoryRepository.getReferenceById(11L));
      Item tunaSalad =
          new Item(
              "Tuna Salad",
              "A container of tuna salad",
              5,
              3.99,
              categoryRepository.getReferenceById(11L));
      Item eggSalad =
          new Item(
              "Egg Salad",
              "A container of egg salad",
              6,
              3.99,
              categoryRepository.getReferenceById(11L));
      Item deliHam =
          new Item(
              "Deli Ham",
              "Sliced, fully cooked deli ham",
              10,
              5.99,
              categoryRepository.getReferenceById(11L));
      Item deliTurkey =
          new Item(
              "Deli Turkey",
              "Sliced, fully cooked deli turkey",
              10,
              6.99,
              categoryRepository.getReferenceById(11L));
      Item deliRoastBeef =
          new Item(
              "Deli Roast Beef",
              "Sliced, fully cooked deli roast beef",
              10,
              7.99,
              categoryRepository.getReferenceById(11L));
      Item deliSalami =
          new Item(
              "Deli Salami",
              "Sliced, fully cooked deli salami",
              10,
              8.99,
              categoryRepository.getReferenceById(11L));
      Item deliPepperoni =
          new Item(
              "Deli Pepperoni",
              "Sliced, fully cooked deli pepperoni",
              10,
              9.99,
              categoryRepository.getReferenceById(11L));
      Item deliChicken =
          new Item(
              "Deli Chicken",
              "Sliced, fully cooked deli chicken",
              10,
              4.99,
              categoryRepository.getReferenceById(11L));
      Item deliTuna =
          new Item(
              "Deli Tuna",
              "Sliced, fully cooked deli tuna",
              10,
              4.99,
              categoryRepository.getReferenceById(11L));
      Item deliEggSalad =
          new Item(
              "Deli Egg Salad",
              "Homemade deli egg salad",
              10,
              4.99,
              categoryRepository.getReferenceById(11L));
      Item deliPotatoSalad =
          new Item(
              "Deli Potato Salad",
              "Homemade deli potato salad",
              10,
              4.99,
              categoryRepository.getReferenceById(11L));
      Item deliPastaSalad =
          new Item(
              "Deli Pasta Salad",
              "Homemade deli pasta salad",
              10,
              4.99,
              categoryRepository.getReferenceById(11L));

      // internationalFoods
      Item sushiNori =
          new Item(
              "Sushi Nori",
              "Dried seaweed sheets for making sushi rolls",
              50,
              2.99,
              categoryRepository.getReferenceById(12L));
      Item chiliCrisp =
          new Item(
              "Chili Crisp",
              "Spicy sauce made with chili peppers and oil",
              100,
              5.99,
              categoryRepository.getReferenceById(12L));
      Item barbacoa =
          new Item(
              "Barbacoa",
              "Mexican-style slow-cooked beef",
              25,
              7.99,
              categoryRepository.getReferenceById(12L));
      Item halalChickenCurry =
          new Item(
              "Halal Chicken Curry",
              "Indian-style chicken curry made with halal meat",
              50,
              9.99,
              categoryRepository.getReferenceById(12L));
      Item kimchi =
          new Item(
              "Kimchi",
              "Spicy fermented cabbage from Korea",
              75,
              3.99,
              categoryRepository.getReferenceById(12L));
      Item fufuFlour =
          new Item(
              "Fufu Flour",
              "Cassava flour for making fufu, a West African staple food",
              25,
              6.99,
              categoryRepository.getReferenceById(12L));
      Item samosas =
          new Item(
              "Samosas",
              "Indian savory pastries filled with potatoes and peas",
              100,
              1.99,
              categoryRepository.getReferenceById(12L));
      Item piriPiriSauce =
          new Item(
              "Piri Piri Sauce",
              "Spicy sauce made with chili peppers and garlic from Portugal",
              50,
              4.99,
              categoryRepository.getReferenceById(12L));
      Item halloumiCheese =
          new Item(
              "Halloumi Cheese",
              "Semi-hard cheese from Cyprus",
              25,
              8.99,
              categoryRepository.getReferenceById(12L));
      Item paoDeQueijo =
          new Item(
              "Pao de Queijo",
              "Brazilian cheese bread made with tapioca flour",
              100,
              3.99,
              categoryRepository.getReferenceById(12L));

      // babyProducts
      Item babyFormula =
          new Item(
              "Baby Formula",
              "Powdered milk-based infant formula",
              50,
              19.99,
              categoryRepository.getReferenceById(13L));
      Item diapers =
          new Item(
              "Diapers",
              "Disposable absorbent underwear for babies",
              100,
              29.99,
              categoryRepository.getReferenceById(13L));
      Item babyWipes =
          new Item(
              "Baby Wipes",
              "Moist towelettes for cleaning a baby's skin and diaper area",
              75,
              9.99,
              categoryRepository.getReferenceById(13L));
      Item teethingToys =
          new Item(
              "Teething Toys",
              "Toys designed to help relieve teething pain in infants",
              25,
              14.99,
              categoryRepository.getReferenceById(13L));
      Item babyLotion =
          new Item(
              "Baby Lotion",
              "Moisturizing lotion for a baby's delicate skin",
              50,
              7.99,
              categoryRepository.getReferenceById(13L));
      Item babyOil =
          new Item(
              "Baby Oil",
              "Oil for massaging and moisturizing a baby's skin",
              25,
              6.99,
              categoryRepository.getReferenceById(13L));
      Item nursingPads =
          new Item(
              "Nursing Pads",
              "Absorbent pads for breastfeeding mothers",
              100,
              12.99,
              categoryRepository.getReferenceById(13L));
      Item babyPowder =
          new Item(
              "Baby Powder",
              "Talc-based powder for preventing diaper rash and absorbing moisture",
              50,
              4.99,
              categoryRepository.getReferenceById(13L));
      Item babyBottleSterilizer =
          new Item(
              "Baby Bottle Sterilizer",
              "Electric appliance for sterilizing baby bottles",
              25,
              39.99,
              categoryRepository.getReferenceById(13L));
      Item babyFood =
          new Item(
              "Baby Food",
              "Pre-made, ready-to-eat food for infants and toddlers",
              100,
              1.99,
              categoryRepository.getReferenceById(13L));

      // petSupplies
      Item dogFood =
          new Item(
              "Dog Food",
              "Dry or canned food for dogs",
              50,
              29.99,
              categoryRepository.getReferenceById(14L));
      Item catFood =
          new Item(
              "Cat Food",
              "Dry or canned food for cats",
              100,
              19.99,
              categoryRepository.getReferenceById(14L));
      Item fishFood =
          new Item(
              "Fish Food",
              "Pellets or flakes for feeding aquarium fish",
              75,
              9.99,
              categoryRepository.getReferenceById(14L));
      Item birdFood =
          new Item(
              "Bird Food",
              "Seeds or pellets for feeding pet birds",
              25,
              14.99,
              categoryRepository.getReferenceById(14L));
      Item hamsterFood =
          new Item(
              "Hamster Food",
              "Seeds and pellets for hamsters and other small rodents",
              50,
              7.99,
              categoryRepository.getReferenceById(14L));
      Item reptileFood =
          new Item(
              "Reptile Food",
              "Crickets, worms, or other live food for reptiles",
              25,
              6.99,
              categoryRepository.getReferenceById(14L));
      Item dogTreats =
          new Item(
              "Dog Treats",
              "Snacks for dogs",
              100,
              12.99,
              categoryRepository.getReferenceById(14L));
      Item catTreats =
          new Item(
              "Cat Treats", "Snacks for cats", 50, 4.99, categoryRepository.getReferenceById(14L));
      Item aquariumFilters =
          new Item(
              "Aquarium Filters",
              "Mechanical, chemical, or biological filters for aquariums",
              25,
              39.99,
              categoryRepository.getReferenceById(14L));
      Item petToys =
          new Item(
              "Pet Toys",
              "Toys for pets to play with",
              100,
              1.99,
              categoryRepository.getReferenceById(14L));

      // healthAndWellnes
      Item vitamins =
          new Item(
              "Vitamins",
              "Dietary supplements in pill or tablet form",
              50,
              19.99,
              categoryRepository.getReferenceById(15L));
      Item painRelief =
          new Item(
              "Pain Relief",
              "Over-the-counter medication for relieving pain",
              100,
              29.99,
              categoryRepository.getReferenceById(15L));
      Item coughAndColdMedicine =
          new Item(
              "Cough and Cold Medicine",
              "Over-the-counter medication for treating coughs and colds",
              75,
              9.99,
              categoryRepository.getReferenceById(15L));
      Item allergyMedicine =
          new Item(
              "Allergy Medicine",
              "Over-the-counter medication for treating allergies",
              25,
              14.99,
              categoryRepository.getReferenceById(15L));
      Item digestiveEnzymes =
          new Item(
              "Digestive Enzymes",
              "Supplement to aid in digestion",
              50,
              7.99,
              categoryRepository.getReferenceById(15L));
      Item fishOil =
          new Item(
              "Fish Oil",
              "Supplement containing omega-3 fatty acids",
              25,
              6.99,
              categoryRepository.getReferenceById(15L));
      Item probiotics =
          new Item(
              "Probiotics",
              "Supplement containing live bacteria to improve gut health",
              100,
              12.99,
              categoryRepository.getReferenceById(15L));
      Item melatonin =
          new Item(
              "Melatonin",
              "Supplement to help with sleep",
              50,
              4.99,
              categoryRepository.getReferenceById(15L));
      Item echinacea =
          new Item(
              "Echinacea",
              "Herbal supplement to boost the immune system",
              25,
              39.99,
              categoryRepository.getReferenceById(15L));
      Item valerianRoot =
          new Item(
              "Valerian Root",
              "Herbal supplement to help with anxiety and sleep",
              100,
              1.99,
              categoryRepository.getReferenceById(15L));

      // beautyAndPersonalCare
      Item shampoo =
          new Item(
              "Shampoo",
              "Hair care product for cleaning the scalp and hair",
              50,
              29.99,
              categoryRepository.getReferenceById(16L));
      Item conditioner =
          new Item(
              "Conditioner",
              "Hair care product for adding moisture to the hair",
              100,
              19.99,
              categoryRepository.getReferenceById(16L));
      Item bodyWash =
          new Item(
              "Body Wash",
              "Product for cleaning the body",
              75,
              9.99,
              categoryRepository.getReferenceById(16L));
      Item lotion =
          new Item(
              "Lotion",
              "Moisturizing product for the skin",
              25,
              14.99,
              categoryRepository.getReferenceById(16L));
      Item toothpaste =
          new Item(
              "Toothpaste",
              "Product for cleaning the teeth and freshening breath",
              50,
              7.99,
              categoryRepository.getReferenceById(16L));
      Item deodorant =
          new Item(
              "Deodorant",
              "Product for reducing body odor",
              25,
              6.99,
              categoryRepository.getReferenceById(16L));
      Item makeupRemover =
          new Item(
              "Makeup Remover",
              "Product for removing makeup from the skin",
              100,
              12.99,
              categoryRepository.getReferenceById(16L));
      Item razors =
          new Item(
              "Razors",
              "Product for removing unwanted body hair",
              50,
              4.99,
              categoryRepository.getReferenceById(16L));
      Item facialCleanser =
          new Item(
              "Facial Cleanser",
              "Product for cleaning the face",
              25,
              39.99,
              categoryRepository.getReferenceById(16L));
      Item bodyScrub =
          new Item(
              "Body Scrub",
              "Exfoliating product for the skin",
              100,
              1.99,
              categoryRepository.getReferenceById(16L));

      // flowers
      Item roses =
          new Item(
              "Roses",
              "Red, pink, or white flowers often given as a symbol of love",
              50,
              29.99,
              categoryRepository.getReferenceById(17L));
      Item tulips =
          new Item(
              "Tulips",
              "Flowers in a variety of colors, often associated with spring",
              100,
              19.99,
              categoryRepository.getReferenceById(17L));
      Item daisies =
          new Item(
              "Daisies",
              "White or yellow flowers with a central disk surrounded by petals",
              75,
              9.99,
              categoryRepository.getReferenceById(17L));
      Item lilies =
          new Item(
              "Lilies",
              "Flowers with large, showy petals, often associated with funerals",
              25,
              14.99,
              categoryRepository.getReferenceById(17L));
      Item carnations =
          new Item(
              "Carnations",
              "Flowers with frilly petals in a range of colors",
              50,
              7.99,
              categoryRepository.getReferenceById(17L));
      Item sunflowers =
          new Item(
              "Sunflowers",
              "Large flowers with a central disk surrounded by yellow petals",
              25,
              6.99,
              categoryRepository.getReferenceById(17L));
      Item orchids =
          new Item(
              "Orchids",
              "Exotic flowers with delicate, often colorful petals",
              100,
              12.99,
              categoryRepository.getReferenceById(17L));
      Item chrysanthemums =
          new Item(
              "Chrysanthemums",
              "Flowers with petals in a range of colors, often associated with fall",
              50,
              4.99,
              categoryRepository.getReferenceById(17L));
      Item gerberaDaisies =
          new Item(
              "Gerbera Daisies",
              "Brightly-colored flowers with a central disk surrounded by petals",
              25,
              39.99,
              categoryRepository.getReferenceById(17L));
      Item peonies =
          new Item(
              "Peonies",
              "Flowers with large, showy petals in a range of colors",
              100,
              1.99,
              categoryRepository.getReferenceById(17L));

      List<Item> items =
          List.of(
              apple,
              banana,
              orange,
              strawberry,
              raspberry,
              blueberry,
              blackberry,
              avocado,
              grapefruit,
              kiwi,
              chicken,
              beef,
              salmon,
              shrimp,
              scallops,
              crab,
              lobster,
              mussels,
              clams,
              oysters,
              milk,
              yogurt,
              cheese,
              butter,
              iceCream,
              sourCream,
              cottageCheese,
              creamCheese,
              eggs,
              milkShake,
              frozenYogurt,
              pizza,
              burritos,
              chickenNuggets,
              iceCreamSandwich,
              frozenDinner,
              frozenPizza,
              frozenYogurt,
              iceCreamCone,
              iceCreamBar,
              frozenWaffles,
              bread,
              bagel,
              croissant,
              muffin,
              donut,
              cinnamonRoll,
              coffeeCake,
              freshBrownies,
              freshCookies,
              cupcakes,
              soda,
              water,
              juice,
              coffee,
              tea,
              beer,
              wine,
              cocktails,
              smoothies,
              energyDrinks,
              sportsDrinks,
              kombucha,
              pasta,
              beans,
              soup,
              cannedVeggies,
              cannedFruit,
              crackers,
              chips,
              granolaBars,
              cereal,
              rice,
              ketchup,
              mustard,
              mayonnaise,
              hotSauce,
              saladDressing,
              barbecueSauce,
              soySauce,
              hoisinSauce,
              teriyakiSauce,
              salsa,
              steakSauce,
              marinaraSauce,
              hotMustard,
              worcestershireSauce,
              toiletPaper,
              paperTowels,
              dishSoap,
              laundryDetergent,
              allPurposeCleaner,
              trashBags,
              lightBulbs,
              airFreshener,
              dishwasherDetergent,
              bleach,
              sponges,
              aluminumFoil,
              glassCleaner,
              towels,
              napkins,
              tissues,
              matches,
              laundryBasket,
              dishDrainer,
              broom,
              candy,
              gum,
              trailMix,
              cookies,
              jerky,
              energyBars,
              popcorn,
              fruitSnacks,
              pretzels,
              cheesePuffs,
              nuts,
              beefJerky,
              candyBars,
              fruitCups,
              riceCakes,
              granola,
              deliMeat,
              deliCheese,
              hummus,
              chickenSalad,
              tunaSalad,
              eggSalad,
              deliHam,
              deliTurkey,
              deliRoastBeef,
              deliSalami,
              deliPepperoni,
              deliChicken,
              deliTuna,
              deliEggSalad,
              deliPotatoSalad,
              deliPastaSalad,
              sushiNori,
              chiliCrisp,
              barbacoa,
              halalChickenCurry,
              kimchi,
              fufuFlour,
              samosas,
              piriPiriSauce,
              halloumiCheese,
              paoDeQueijo,
              babyFormula,
              diapers,
              babyWipes,
              teethingToys,
              babyLotion,
              babyOil,
              nursingPads,
              babyPowder,
              babyBottleSterilizer,
              babyFood,
              dogFood,
              catFood,
              fishFood,
              birdFood,
              hamsterFood,
              reptileFood,
              dogTreats,
              catTreats,
              aquariumFilters,
              petToys,
              vitamins,
              painRelief,
              coughAndColdMedicine,
              allergyMedicine,
              digestiveEnzymes,
              fishOil,
              probiotics,
              melatonin,
              echinacea,
              valerianRoot,
              shampoo,
              conditioner,
              bodyWash,
              lotion,
              toothpaste,
              deodorant,
              makeupRemover,
              razors,
              facialCleanser,
              bodyScrub,
              roses,
              tulips,
              daisies,
              lilies,
              carnations,
              sunflowers,
              orchids,
              chrysanthemums,
              gerberaDaisies,
              peonies);

      repository.saveAll(items);
    };
  }
}
