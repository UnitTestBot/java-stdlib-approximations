package approximations.java.util.Random;

public class LXMRandomWithSeedTest {
    /*public static void main(String[] args) {
        byte[] seed = new byte[0x100];
        for (int i = 0; i < seed.length; ++i) {
            seed[i] = (byte) i;
        }
        java.util.List<RandomGeneratorFactory<java.util.random.RandomGenerator>> lxmFactories = RandomGeneratorFactory.all()
                .filter(factory -> factory.group().equals("LXM"))
                .toList();
        for (var lxmFactory : lxmFactories) {
            var lxmGen0 = lxmFactory.create(seed);
            var lxmGen1 = lxmFactory.create(seed);
            if (lxmGen0.nextLong() != lxmGen1.nextLong()) {
                throw new RuntimeException("%s(byte[]) is incorrect".formatted(lxmFactory.name()));
            }
        }
    }*/
}
