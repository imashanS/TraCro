import Navbar from "@/components/Navbar";
import Hero from "@/components/Hero";
import Features from "@/components/Features";
import Stats from "@/components/Stats";
import CTA from "@/components/CTA";
import Footer from "@/components/Footer";

export default function Home() {
  return (
      <main className="min-h-screen bg-[#080808] text-white overflow-x-hidden">
        <Navbar />
        <Hero />
        <Stats />
        <Features />
        <CTA />
        <Footer />
      </main>
  );
}