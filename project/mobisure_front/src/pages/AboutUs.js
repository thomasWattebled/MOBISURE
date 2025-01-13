import React from 'react';
import '../assets/css/aboutUs.css';
import suzanne from '../assets/image/aboutUs/suzanne.jpg';
import hector from '../assets/image/aboutUs/hector.webp';
import marius from '../assets/image/aboutUs/marius.png';

const AboutUs = () => (
  <div className="container mt-5">
  
    <h1 id="titre">AssurMob</h1>
	
	<p id="ss-titre">Découvrez une assurance abordable pour tous avec une grande diversité de service.</p>
	
	<div id="mission" className='bloc'>
		<h2>Notre mission ? </h2>
		<p>
			Chez AssurMob, nous nous engageons à fournir une assistance fiable aux voyageur du monde entier.
		</p>
		<p>
			Notre objectif est d'être à votre disposition, où que vous soyez, peut importe votre activité (travail ou loisirs) afin d' être la lorsque vous en avez le plus besoin.
		</p>
		<p>
			Nous proposons une large gamme de services (véhicules, voyages, médicales, etc...).
			Notre équipe dédiée est là pour s'assurer que votre voyage se déroule le plus facilement possible.
		</p>
	</div>
	
	<div id="sommes" className='bloc'>
		<h2>Qui sommes ?</h2>
		
		<p>
			Nous sommes une équipe diversifiée d’experts qui travaillent ensemble pour fournir des services de premier ordre à nos clients. 
			Grâce à une combinaison de professionnels expérimentés dans les domaines de la logistique, de la santé, de la technologie et du service client, nous sommes prêts à répondre à toute situation avec des solutions rapides, efficaces et personnalisées.
		</p>
		
		<div className='interview'>
			<span>Marius (Responsable du développement)</span>
			<img src={marius}/>
			<p>
				Possédant 47 ans d’expérience, Marius est la force motrice de nos innovations technologiques. 
				En tant que dirigeant de notre entreprise, il supervise le développement de nouveaux services, s’assurant que nous répondions aux besoins en constante évolution de nos clients. 
				Il pilote également nos initiatives de développement durable, s’efforçant de réduire notre empreinte carbone.
			</p>
		</div>
		
		<div className='interview'>
			<span>Suzanne (Spécialiste en logistique, Asie du Sud-Est)</span>
			<img src={suzanne}/>
			<p>
				Basée à Jakarta, Suzanne apporte à l’entreprise son expertise en matière de logistique et d’impact environnemental. 
				Elle joue un rôle clé dans la résolution de situations complexes pour nos clients en Asie du Sud-Est et négocie avec des partenaires locaux pour garantir le meilleur service au coût carbone le plus bas.
			</p>
		</div>
		
		<div className='interview'>
			<span>Hector (spécialiste des soins d'urgence)</span>
			<img src={hector}/>
			<p>
				Hector, un médecin urgentiste de 45 ans, fournit des soins médicaux de haut niveau et une coordination logistique à nos clients qui ont besoin d'une assistance urgente. 
				Qu'il s'agisse d'une urgence locale ou d'une évacuation internationale, Hector est prêt à assurer les meilleurs soins possibles, même dans les situations les plus difficiles.
			</p>
		</div>
		
	</div>

	<div className='bloc'>
		<h2>Ce que nous faisons ?</h2>
		
		<p>
			Nous proposons une variété de services conçus pour aider nos clients pendant leurs voyages, des soins médicaux aux solutions logistiques :
		</p>
		
		<div className='solutions'>
			<p>
				Assistance d'urgence : que vous soyez blessé ou malade, notre équipe médicale est disponible 24 h/24 et 7 j/7 pour fournir une consultation à distance ou déployer une assistance en cas de besoin.
			</p>
		</div>
		
		<div className='solutions'>
			<p>
				Support logistique : nous aidons à résoudre les problèmes de transport, tels que les pannes de véhicule, la recherche d'un logement ou l'assistance au transport local comme les vélos ou les scooters.
			</p>
		</div>
		
		<div className='solutions'>
			<p>
				Initiatives de développement durable : dans le cadre de notre engagement envers l'environnement, nous intégrons des options de réduction des émissions de carbone dans nos services et encourageons les clients à faire des choix durables.
			</p>
		</div>
		
		<div className='solutions'>
			<p>
				Données et rapports : nos clients, qu'ils soient particuliers ou entreprises, peuvent suivre leur historique de voyage et d'assistance, recevoir des rapports détaillés et obtenir des informations sur l'impact environnemental de leurs décisions de voyage.
			</p>
		</div>
		
	</div>	
	
	
  </div>
);

export default AboutUs;
