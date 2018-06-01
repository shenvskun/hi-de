package sk.base.learn_enum;

public enum Animal {
		Shizi("lion") {
			@Override
			public String speak() {
				return "aaa";
			}
		},Laohu("tiger") {
			@Override
			public String speak() {
				return "bbb";
			}
		};
		private String name;
		private Animal(String name){
			this.name = name;
		} 
		public String getName() {
			return name;
		}
		
		public abstract String speak();
		
		public static void main(String[] args) {
			System.out.println(Animal.Shizi.speak());
		}
}
